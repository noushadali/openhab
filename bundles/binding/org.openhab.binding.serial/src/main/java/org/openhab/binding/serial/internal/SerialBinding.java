package org.openhab.binding.serial.internal;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.openhab.core.events.EventPublisher;
import org.openhab.core.events.EventSubscriber;
import org.openhab.core.items.Item;
import org.openhab.core.library.items.StringItem;
import org.openhab.core.library.items.SwitchItem;
import org.openhab.core.library.types.StringType;
import org.openhab.core.types.Command;
import org.openhab.core.types.State;
import org.openhab.model.item.binding.BindingConfigParseException;
import org.openhab.model.item.binding.BindingConfigReader;

public class SerialBinding implements EventSubscriber, BindingConfigReader {

	private Map<String, SerialDevice> serialDevices = new HashMap<String, SerialDevice>();

	/** stores information about the which items are associated to which port. The map has this content structure: itemname -> port */ 
	private Map<String, String> itemMap = new HashMap<String, String>();
	
	/** stores information about the context of items. The map has this content structure: context -> Set of itemNames */ 
	private Map<String, Set<String>> contextMap = new HashMap<String, Set<String>>();

	private EventPublisher eventPublisher = null;
	
	public void setEventPublisher(EventPublisher eventPublisher) {
		this.eventPublisher = eventPublisher;
		
		for(SerialDevice serialDevice : serialDevices.values()) {
			serialDevice.setEventPublisher(eventPublisher);
		}
	}
	
	public void unsetEventPublisher(EventPublisher eventPublisher) {
		this.eventPublisher = null;

		for(SerialDevice serialDevice : serialDevices.values()) {
			serialDevice.setEventPublisher(null);
		}
	}
	
	@Override
	public void receiveCommand(String itemName, Command command) {
		if(itemMap.keySet().contains(itemName)) {
			SerialDevice serialDevice = serialDevices.get(itemMap.get(itemName));
			if(command instanceof StringType) {
				serialDevice.writeString(command.toString());
			}
		}
	}

	@Override
	public void receiveUpdate(String itemName, State newStatus) {
		// ignore any updates
	}

	@Override
	public String getBindingType() {
		return "serial";
	}

	@Override
	public void processBindingConfiguration(String context, Item item, String bindingConfig)
			throws BindingConfigParseException {
		if(item instanceof SwitchItem || item instanceof StringItem) {		
			String port = bindingConfig;
			SerialDevice serialDevice = serialDevices.get(port);
			if(serialDevice==null) {
				serialDevice = new SerialDevice(port);
				serialDevice.setEventPublisher(eventPublisher);
				try {
					serialDevice.initialize();
				} catch (InitializationException e) {
					throw new BindingConfigParseException("Could not open serial port " + port + ": " + e.getMessage());
				} catch (Throwable e) {
					throw new BindingConfigParseException("Could not open serial port " + port + ": " + e.getMessage());
				}
			}
			if(item instanceof StringItem) {
				if(serialDevice.getStringItemName()==null) {
					serialDevice.setStringItemName(item.getName());
				} else {
					throw new BindingConfigParseException("There is already another StringItem assigned to serial port " + port);
				}
			} else { // it is a SwitchItem
				if(serialDevice.getSwitchItemName()==null) {
					serialDevice.setSwitchItemName(item.getName());
				} else {
					throw new BindingConfigParseException("There is already another SwitchItem assigned to serial port " + port);
				}
			}
			Set<String> itemNames = contextMap.get(context);
			if(itemNames==null) {
				itemNames = new HashSet<String>();
				contextMap.put(context, itemNames);
			}
			itemNames.add(item.getName());
		} else {
			throw new BindingConfigParseException("Serial binding only supports Switch and String items!");
		}
		
	}

	@Override
	public void removeConfigurations(String context) {
		Set<String> itemNames = contextMap.get(context);
		if(itemNames!=null) {
			for(String itemName : itemNames) {
				// we remove all information in the serial devices
				SerialDevice serialDevice = serialDevices.get(itemMap.get(itemName));
				if(itemName.equals(serialDevice.getStringItemName())) {
					serialDevice.setStringItemName(null);
				}
				if(itemName.equals(serialDevice.getSwitchItemName())) {
					serialDevice.setSwitchItemName(null);
				}
				// if there is no binding left, dispose this device
				if(serialDevice.getStringItemName()==null && serialDevice.getSwitchItemName()==null) {
					serialDevice.close();
					serialDevices.remove(serialDevice.getPort());
				}
			}
			contextMap.remove(context);
		}
	}

}
