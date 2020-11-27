public class TaxBreakdown {
    private final int FederalIncomeTax;
    private final int StateIncomeTax;
    private final int SocialSecurityTax;
    private final int MedicareTax;
    private final int StateDisabilityInsuranceTax;

    TaxBreakdown(int FederalIncome, int StateIncome, int SocialSecurity, int Medicare, int StateDisabilityInsurance)
    {
        FederalIncomeTax = FederalIncome;
        StateIncomeTax = StateIncome;
        SocialSecurityTax = SocialSecurity;
        MedicareTax = Medicare;
        StateDisabilityInsuranceTax = StateDisabilityInsurance;
    }

    public int getFederalIncomeTax()
    {
        return FederalIncomeTax;
    }

    public int getStateIncomeTax()
    {
        return StateIncomeTax;
    }

    public int getSocialSecurityTax()
    {
        return SocialSecurityTax;
    }

    public int getMedicareTax()
    {
        return MedicareTax;
    }

    public int getStateDisabilityInsuranceTax()
    {
        return StateDisabilityInsuranceTax;
    }
}
