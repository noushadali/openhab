/**
 * openHAB, the open Home Automation Bus.
 * Copyright (C) 2011, openHAB.org <admin@openhab.org>
 *
 * See the contributors.txt file in the distribution for a
 * full listing of individual contributors.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation; either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, see <http://www.gnu.org/licenses>.
 *
 * Additional permission under GNU GPL version 3 section 7
 *
 * If you modify this Program, or any covered work, by linking or
 * combining it with Eclipse (or a modified version of that library),
 * containing parts covered by the terms of the Eclipse Public License
 * (EPL), the licensors of this Program grant you additional permission
 * to convey the resulting work.
 */

/*
 * generated by Xtext
 */
package org.openhab.model.rule;

import org.eclipse.xtext.scoping.IScopeProvider;
import org.eclipse.xtext.xbase.featurecalls.IdentifiableSimpleNameProvider;
import org.eclipse.xtext.xbase.scoping.featurecalls.StaticMethodsFeatureForTypeProvider.ExtensionClassNameProvider;
import org.eclipse.xtext.xbase.typing.ITypeArgumentContextHelper;
import org.eclipse.xtext.xbase.typing.ITypeProvider;
import org.openhab.core.script.jvmmodel.ScriptIdentifiableSimpleNameProvider;
import org.openhab.core.script.scoping.StateAndCommandProvider;
import org.openhab.model.rule.scoping.RuleExtensionClassNameProvider;
import org.openhab.model.rule.scoping.RulesScopeProvider;
import org.openhab.model.rule.typing.RuleTypeProvider;


/**
 * Use this class to register components to be used at runtime / without the Equinox extension registry.
 */
@SuppressWarnings("restriction")
public class RulesRuntimeModule extends org.openhab.model.rule.AbstractRulesRuntimeModule {

	@Override
	public Class<? extends IdentifiableSimpleNameProvider> bindIdentifiableSimpleNameProvider() {
		return ScriptIdentifiableSimpleNameProvider.class;
	}

	@Override
	public Class<? extends ITypeProvider> bindITypeProvider() {
		return RuleTypeProvider.class;
	}
	
	public Class<? extends ITypeArgumentContextHelper> bindITypeArgumentContextHelper() {
		return RuleTypeProvider.class;
	}
	
	public Class<? extends ExtensionClassNameProvider> bindExtensionClassNameProvider() {
		return RuleExtensionClassNameProvider.class;
	}
	
	public Class<StateAndCommandProvider> bindStateAndCommandProvider() {
		return StateAndCommandProvider.class;
	}
	
	@Override
	public Class<? extends IScopeProvider> bindIScopeProvider() {
		return RulesScopeProvider.class;
	}
}
