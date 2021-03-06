package de.uniorg.ui5helper.ui5;

import com.google.gson.JsonObject;

public class ReturnValueDocumentation extends AbstractApiSymbol {
    private String type;

    public String getType() {
        return type;
    }

    static ReturnValueDocumentation fromJsonDoc(JsonObject doc) {
        ReturnValueDocumentation pdoc = new ReturnValueDocumentation();
        ParserUtil parser = new ParserUtil(doc);

        pdoc.name = "ReturnValue";
        pdoc.description = parser.getDescription();
        pdoc.type = parser.getString("type", "");

        return pdoc;
    }
}
