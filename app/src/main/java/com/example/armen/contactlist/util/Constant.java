package com.example.armen.contactlist.util;


public class Constant {

    public class API {
        public static final String HOST = "https://raw.githubusercontent.com/Armen63/armenasatryan.github.io";
        public static final String CONTACT_LIST = HOST + "/master/contact_list/contacts.json";
        public static final String CONTACT_ITEM = HOST + "/master/contact_list/contacts/"; // + id
        public static final String CONTACT_ITEM_POSTFIX = "/details.json";
    }

    public class Action {
        public static final String ACTION_UPLOAD = "ACTION_UPLOAD";
    }

    public class Argument {
        public static final String ARGUMENT_DATA = "ARGUMENT_DATA";
    }

    public class Extra {
        public static final String EXTRA_USER = "EXTRA_USER";
        public static final String EXTRA_CONTACT = "EXTRA_CONTACT";
        public static final String EXTRA_CONTACT_ID = "EXTRA_CONTACT_ID";
    }

    public class POJO {
        public static final String CONTACTS = "contacts";

        public static final String CONTACT_ID = "contact_id";
        public static final String FIRST_NAME = "first_name";
        public static final String LAST_NAME = "last_name";
        public static final String PHONE = "phone";
        public static final String EMAIL = "email";
        public static final String NOTE = "note";
        public static final String IMAGE = "image";
    }

    public class Preferances {
        public static final String PREFERENCES_NAME = "PREFERENCES_NAME";
        public static final String USER_ID = "USER_ID";
        public static final String LOGGED_IN = "LOGGED_IN";
    }

    public class Bundle {
    }

    public class Symbol {
        public static final String ASTERISK = "*";
        public static final String NEW_LINE = "\n";
        public static final String SPACE = " ";
        public static final String NULL = "";
        public static final String COLON = ":";
        public static final String COMMA = ",";
        public static final String SLASH = "/";
        public static final String DOT = ".";
        public static final String UNDERLINE = "_";
        public static final String DASH = "-";
        public static final String AT = "@";
        public static final String AMPERSAND = "&";
    }

    public class Boolean {
        public static final String TRUE = "true";
        public static final String FALSE = "false";
    }

    public class Util {
        public static final int QUALITY = 100;
        public static final String ANDROID_DATA_ROOT = "Android/data/";
        public static final String SD = "file://";
        public static final String SHA = "SHA";
        public static final String UTF_8 = "UTF-8";
    }

    public class Identifier {
        public static final String ID = "id";
        public static final String ANDROID = "android.support";
        public static final String ALERT_TITLE = "alertTitle";
    }

    public class BuildType {
        public static final String RELEASE = "release";
        public static final String DEBUG = "debug";
    }

    public class RequestMode {
        public static final int INITIAL = 1;
        public static final int UPDATE = 2;
        public static final int NEXT = 3;
        public static final int NONE = 4;
        public static final int PREVIOUS = 5;
    }

    public class MapType {
        public static final int NORMAL_MAP_TYPE = 1;
        public static final int SATELLITE_MAP_TYPE = 2;
    }

    public class Build {
        public static final String RELEASE = "release";
        public static final String DEBUG = "debug";
    }


}
