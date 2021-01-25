package framework.guidewire.pages;

import framework.elements.Identifier;
import framework.elements.enums.ElementType;
import org.openqa.selenium.By;

public class GWIDs {
    public static final Identifier QUICK_JUMP = new Identifier(By.name("QuickJump"));
    public static final Identifier SETTINGS_COG = new Identifier(By.id("gw-TabBarWidget--settings"), ElementType.ELEMENT);


    public static class Login{
        public static final Identifier USER_NAME = new Identifier(By.name("Login-LoginScreen-LoginDV-username"), ElementType.TEXT_BOX);
        public static final Identifier PASSWORD = new Identifier(By.name("Login-LoginScreen-LoginDV-password"), ElementType.TEXT_BOX);
        public static final Identifier LOGIN = new Identifier(By.id("Login-LoginScreen-LoginDV-submit"), ElementType.ELEMENT);
        public static final Identifier LOGIN_MESSAGES = new Identifier(By.id("Login-LoginScreen-1"), ElementType.ELEMENT);
    }

    public static class SettingsCog{
        public static final Identifier LOGOUT = new Identifier(By.id("TabBar-LogoutTabBarLink"), ElementType.LINK);
    }



    public static final class ServerPages{
        public static final Identifier ACTIONS_MENU = new Identifier(By.id("ServerTools-InternalToolsMenuActions"), ElementType.LINK);

        public static final class ActionsMenu{
            public static final Identifier RETURN_TO_APP = new Identifier(By.id("ServerTools-InternalToolsMenuActions-ReturnToApp"), ElementType.LINK);
        }


        public static final class ServerTools{
            public static final Identifier SET_LOG_LEVEL = new Identifier(By.id("ServerTools-MenuLinks-ServerTools_SetLogLevel"), ElementType.LINK);

            public static final class LogLevel{
                public static final Identifier LOGGERS = new Identifier(By.name("SetLogLevel-SetLogLevelScreen-Logger"), ElementType.SELECT_BOX);
                public static final Identifier LEVELS = new Identifier(By.name("SetLogLevel-SetLogLevelScreen-NewLevel"), ElementType.SELECT_BOX);
                public static final Identifier SET_LEVEL = new Identifier(By.id("SetLogLevel-SetLogLevelScreen-SetLevel"), ElementType.BUTTON);
                public static final Identifier CURRENT_LEVEL = new Identifier(By.id("SetLogLevel-SetLogLevelScreen-CurActualLevel"), ElementType.ELEMENT);
            }
        }
    }
}
