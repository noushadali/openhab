lexer grammar InternalSitemap;
@header {
package org.openhab.model.parser.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.parser.antlr.Lexer;
}

T11 : 'sitemap' ;
T12 : 'label=' ;
T13 : 'icon=' ;
T14 : '{' ;
T15 : '}' ;
T16 : 'Frame' ;
T17 : 'item=' ;
T18 : 'Text' ;
T19 : 'Group' ;
T20 : 'Image' ;
T21 : 'url=' ;
T22 : 'Switch' ;
T23 : 'mappings=[' ;
T24 : ',' ;
T25 : ']' ;
T26 : 'Slider' ;
T27 : 'sendFrequency=' ;
T28 : 'switchSupport' ;
T29 : 'Selection' ;
T30 : 'List' ;
T31 : 'separator=' ;
T32 : '=' ;

// $ANTLR src "../org.openhab.model.sitemap/src-gen/org/openhab/model/parser/antlr/internal/InternalSitemap.g" 1743
RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_'|'0'..'9')+;

// $ANTLR src "../org.openhab.model.sitemap/src-gen/org/openhab/model/parser/antlr/internal/InternalSitemap.g" 1745
RULE_INT : ('0'..'9')+;

// $ANTLR src "../org.openhab.model.sitemap/src-gen/org/openhab/model/parser/antlr/internal/InternalSitemap.g" 1747
RULE_STRING : ('"' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'"')))* '"'|'\'' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'\'')))* '\'');

// $ANTLR src "../org.openhab.model.sitemap/src-gen/org/openhab/model/parser/antlr/internal/InternalSitemap.g" 1749
RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

// $ANTLR src "../org.openhab.model.sitemap/src-gen/org/openhab/model/parser/antlr/internal/InternalSitemap.g" 1751
RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

// $ANTLR src "../org.openhab.model.sitemap/src-gen/org/openhab/model/parser/antlr/internal/InternalSitemap.g" 1753
RULE_WS : (' '|'\t'|'\r'|'\n')+;

// $ANTLR src "../org.openhab.model.sitemap/src-gen/org/openhab/model/parser/antlr/internal/InternalSitemap.g" 1755
RULE_ANY_OTHER : .;

