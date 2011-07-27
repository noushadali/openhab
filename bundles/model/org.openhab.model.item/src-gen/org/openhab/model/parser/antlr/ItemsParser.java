/*
* generated by Xtext
*/
package org.openhab.model.parser.antlr;

import com.google.inject.Inject;

import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.openhab.model.services.ItemsGrammarAccess;

public class ItemsParser extends org.eclipse.xtext.parser.antlr.AbstractAntlrParser {
	
	@Inject
	private ItemsGrammarAccess grammarAccess;
	
	@Override
	protected void setInitialHiddenTokens(XtextTokenStream tokenStream) {
		tokenStream.setInitialHiddenTokens("RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT");
	}
	
	@Override
	protected org.openhab.model.parser.antlr.internal.InternalItemsParser createParser(XtextTokenStream stream) {
		return new org.openhab.model.parser.antlr.internal.InternalItemsParser(stream, getGrammarAccess());
	}
	
	@Override 
	protected String getDefaultRuleName() {
		return "ItemModel";
	}
	
	public ItemsGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}
	
	public void setGrammarAccess(ItemsGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}
	
}