package framework.enums;

public enum ApplicationNames {
    CC("ClaimCenter"),
    BC("BillingCenter"),
    PC("PolicyCenter"),
    AB("ContactManager"),
    ACCOUNT_MANAGEMENT_PORTAL("CustomerEngageAccountManagementPortal"),
    FINANCE_CORE("FinanceCore");

    private final String fullName;

    ApplicationNames(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }
}
