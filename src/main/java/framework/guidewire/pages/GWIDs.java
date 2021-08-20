package framework.guidewire.pages;

import framework.elements.Identifier;
import org.openqa.selenium.By;

public class GWIDs {
    public static final Identifier QUICK_JUMP = new Identifier(By.name("QuickJump"), "QuickJump");
    public static final Identifier SETTINGS_COG = new Identifier(By.id("gw-TabBarWidget--settings"), "Settings Cog");
    public static final Identifier ERROR_MESSAGES = new Identifier(By.xpath("//div[@class='gw-WebMessage']//div[contains(@class, 'gw-alert-error')]//div[@class='gw-message'] | //div[contains(@class, 'gw-MessagesWidget--group-error')]//div[@class='gw-message'] | //div[@id='gw-serverError']//div[@class='gw-header']"), "Error Messages");
    public static final Identifier INFO_MESSAGES = new Identifier(By.xpath("//div[@class='gw-WebMessage']//div[contains(@class, 'gw-alert-info')]//div[@class='gw-message'] | //div[contains(@class, 'gw-MessagesWidget--group-info')]//div[@class='gw-message']"), "Info Messages");
    public static final Identifier UNSAVED_WORK = new Identifier(By.id("TabBar-UnsavedWorkTabBarLink"), "Unsaved Work");
    public static final Identifier BOTTOM_CENTER_SHEET = new Identifier(By.id("gw-center-bottom-section"), "Center Bottom sheet on the screen");


    public static class Login {
        public static final Identifier USER_NAME = new Identifier(By.name("Login-LoginScreen-LoginDV-username"), "Login Username");
        public static final Identifier PASSWORD = new Identifier(By.name("Login-LoginScreen-LoginDV-password"), "Login Password");
        public static final Identifier LOGIN = new Identifier(By.id("Login-LoginScreen-LoginDV-submit"), "Login Submit");
        public static final Identifier LOGIN_MESSAGES = new Identifier(By.id("Login-LoginScreen-1"), "Login Messages");
    }

    public static class SettingsCog {
        public static final Identifier LOGOUT = new Identifier(By.id("TabBar-LogoutTabBarLink"), "Logout Link", true);
    }


    public static final class ServerPages {
        public static final Identifier ACTIONS_MENU = new Identifier(By.id("ServerTools-InternalToolsMenuActions"), "Server Pages Actions Menu");

        public static final class ActionsMenu {
            public static final Identifier RETURN_TO_APP = new Identifier(By.id("ServerTools-InternalToolsMenuActions-ReturnToApp"), "Server Pages Return link");
        }


        public static final class ServerTools {
            public static final Identifier SET_LOG_LEVEL = new Identifier(By.id("ServerTools-MenuLinks-ServerTools_SetLogLevel"), "Set Log Level Button");

            public static final class LogLevel {
                public static final Identifier LOGGERS = new Identifier(By.name("SetLogLevel-SetLogLevelScreen-Logger"), "Loggers Dropdown");
                public static final Identifier LEVELS = new Identifier(By.name("SetLogLevel-SetLogLevelScreen-NewLevel"), "Logging levels dropdown");
                public static final Identifier SET_LEVEL = new Identifier(By.id("SetLogLevel-SetLogLevelScreen-SetLevel"), "Set log level button");
                public static final Identifier CURRENT_LEVEL = new Identifier(By.id("SetLogLevel-SetLogLevelScreen-CurActualLevel"), "Current Logging level");
            }
        }
    }
}
