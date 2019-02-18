package framework.responsibilities;

import java.util.HashMap;

public class Initializer {
    static {
        HashMap<String, String> GW8_ENVIRONMENTS = new HashMap<>();
        // CC ENVIRONMENTS
        GW8_ENVIRONMENTS.put("CC8DEV", "http://cc8dev/cc/ClaimCenter.do");
        GW8_ENVIRONMENTS.put("CC8DEV3", "http://cc8dev3/cc/ClaimCenter.do");
        GW8_ENVIRONMENTS.put("CC8UAT", "http://cc8uat/cc/ClaimCenter.do");
        GW8_ENVIRONMENTS.put("CC8BUAT", "http://cc8buat/cc/ClaimCenter.do");
        GW8_ENVIRONMENTS.put("CC8UAT2", "http://fbmsgwccb-uat82/cc/ClaimCenter.do");
        GW8_ENVIRONMENTS.put("CC8QA", "http://cc8qa/cc/ClaimCenter.do");
        GW8_ENVIRONMENTS.put("CC8IT", "http://cc8it/cc/ClaimCenter.do");
        GW8_ENVIRONMENTS.put("CC8STAB01", "http://fbmsgw-stab01:8080/cc/ClaimCenter.do");
        GW8_ENVIRONMENTS.put("CC8STAB02", "http://fbmsgw-stab02:8080/cc/ClaimCenter.do");
        GW8_ENVIRONMENTS.put("CC8STAB04", "http://fbmsgw-stab04:8080/cc/ClaimCenter.do");
        // PC ENVIRONMENTS
        GW8_ENVIRONMENTS.put("PC8DEV", "http://pc8dev/pc/PolicyCenter.do");
        GW8_ENVIRONMENTS.put("PC8DEV3", "http://pc8dev3/pc/PolicyCenter.do");
        GW8_ENVIRONMENTS.put("PC8UAT", "http://pc8uat/pc/PolicyCenter.do");
        GW8_ENVIRONMENTS.put("PC8BUAT", "http://pc8buat/pc/PolicyCenter.do");
        GW8_ENVIRONMENTS.put("PC8UAT2", "http://pc8buat2/pc/PolicyCenter.do");
        GW8_ENVIRONMENTS.put("PC8QA", "http://pc8qa/pc/PolicyCenter.do");
        GW8_ENVIRONMENTS.put("PC8IT", "http://pc8it/pc/PolicyCenter.do");
        GW8_ENVIRONMENTS.put("PC8STAB01", "http://fbmsgw-stab01:8180/pc/PolicyCenter.do");
        GW8_ENVIRONMENTS.put("PC8STAB02", "http://fbmsgw-stab02:8180/pc/PolicyCenter.do");
        GW8_ENVIRONMENTS.put("PC8STAB03", "http://fbmsgw-stab03:8180/pc/PolicyCenter.do");
        GW8_ENVIRONMENTS.put("PC8STAB04", "http://fbmsgw-stab04:8180/pc/PolicyCenter.do");
        GW8_ENVIRONMENTS.put("PC8STAB05", "http://fbmsgw-stab05:8180/pc/PolicyCenter.do");
        // BC ENVIRONMENTS
        GW8_ENVIRONMENTS.put("BC8DEV", "http://bc8dev/bc/BillingCenter.do");
        GW8_ENVIRONMENTS.put("BC8UAT", "http://bc8uat/bc/BillingCenter.do");
        GW8_ENVIRONMENTS.put("BC8BUAT", "http://bc8buat/bc/BillingCenter.do");
        GW8_ENVIRONMENTS.put("BC8QA", "http://bc8qa/bc/BillingCenter.do");
        GW8_ENVIRONMENTS.put("BC8IT", "http://bc8it/bc/BillingCenter.do");
        // BC ENVIRONMENTS
        GW8_ENVIRONMENTS.put("AB8DEV", "http://ab8dev/ab/ContactManger.do");
        GW8_ENVIRONMENTS.put("AB8DEV3", "http://ab8dev3/ab/ContactManger.do");
        GW8_ENVIRONMENTS.put("AB8UAT", "http://ab8uat/ab/ContactManger.do");
        GW8_ENVIRONMENTS.put("AB8BUAT", "http://ab8buat/ab/ContactManger.do");
        GW8_ENVIRONMENTS.put("AB8UAT2", "http://ab8uat2/ab/ContactManger.do");
        GW8_ENVIRONMENTS.put("AB8IT", "http://ab8it/ab/ContactManger.do");
        GW8_ENVIRONMENTS.put("AB8QA", "http://ab8qa/ab/ContactManger.do");
    }
}
