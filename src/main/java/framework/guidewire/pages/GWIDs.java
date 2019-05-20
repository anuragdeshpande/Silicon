package framework.guidewire.pages;

import framework.elements.Identifier;
import framework.elements.enums.ElementType;
import org.openqa.selenium.By;

public class GWIDs {
    public static final Identifier LIST_OPTIONS = new Identifier(By.cssSelector("div.x-boundlist.x-boundlist-floating.x-boundlist-default.x-layer.x-border-box:not([style*='display: none'])"));
    public static final Identifier QUICK_JUMP = new Identifier(By.id("QuickJump-inputEl"));
    public static final Identifier ERROR_MESSAGE = new Identifier(By.xpath("//div[@class='message']/img[@class='error_icon']/parent::div"));
    public static final Identifier CONFIRMATION_WINDOW = new Identifier(By.cssSelector("div[id*='messagebox-']"));
    public static final Identifier SETTINGS_COG = new Identifier(By.id(":TabLinkMenuButton-btnIconEl"), ElementType.ELEMENT);


    public static class Login{
        public static final Identifier USER_NAME = new Identifier(By.id("Login:LoginScreen:LoginDV:username-inputEl"), ElementType.TEXT_BOX);
        public static final Identifier PASSWORD = new Identifier(By.id("Login:LoginScreen:LoginDV:password-inputEl"), ElementType.TEXT_BOX);
        public static final Identifier LOGIN = new Identifier(By.id("Login:LoginScreen:LoginDV:submit-btnInnerEl"), ElementType.ELEMENT);
    }

    public static class SettingsCog{
        public static final Identifier LOGOUT = new Identifier(By.id("TabBar:LogoutTabBarLink-textEl"), ElementType.LINK);
    }



    public static final class ServerPages{
        public static final Identifier ACTIONS_MENU = new Identifier(By.id("ServerTools:InternalToolsMenuActions-btnInnerEl"), ElementType.LINK);

        public static final class ActionsMenu{
            public static final Identifier RETURN_TO_APP = new Identifier(By.id("ServerTools:InternalToolsMenuActions:ReturnToApp-textEl"), ElementType.LINK);
        }


        public static final class ServerTools{
            public static final Identifier SET_LOG_LEVEL = new Identifier(By.id("ServerTools:MenuLinks:ServerTools_SetLogLevel"), ElementType.LINK);

            public static final class LogLevel{
                public static final Identifier LOGGERS = new Identifier(By.id("SetLogLevel:SetLogLevelScreen:Categories-inputEl"), ElementType.LINK);
                public static final Identifier LEVELS = new Identifier(By.id("SetLogLevel:SetLogLevelScreen:CategoriesLevels-inputEl"), ElementType.LINK);
                public static final Identifier SET_LEVEL = new Identifier(By.id("SetLogLevel:SetLogLevelScreen:CategorySetLevel-btnInnerEl"), ElementType.LINK);
                public static final Identifier CURRENT_LEVEL = new Identifier(By.id("SetLogLevel:SetLogLevelScreen:CategoryOldLevel-inputEl"), ElementType.ELEMENT);
            }
        }
    }
}
