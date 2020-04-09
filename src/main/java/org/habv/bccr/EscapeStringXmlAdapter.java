package org.habv.bccr;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 *
 * @author Herman Barrantes
 */
public class EscapeStringXmlAdapter extends XmlAdapter<String, String> {

    private static final String LT_ESCAPE = "&lt;";
    private static final String LT_UNESCAPE = "<";
    private static final String GT_ESCAPE = "&gt;";
    private static final String GT_UNESCAPE = ">";

    @Override
    public String unmarshal(String value) throws Exception {
        if (value == null) {
            return null;
        }
        return value
                .replaceAll(LT_ESCAPE, LT_UNESCAPE)
                .replaceAll(GT_ESCAPE, GT_UNESCAPE);
    }

    @Override
    public String marshal(String value) throws Exception {
        if (value == null) {
            return null;
        }
        return value
                .replaceAll(LT_UNESCAPE, LT_ESCAPE)
                .replaceAll(GT_UNESCAPE, GT_ESCAPE);
    }

}
