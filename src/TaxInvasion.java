public class TaxInvasion {
    public static void main(String[] args)
    {
        TaxBracketService taxService = new TaxBracketService2020();
        //TaxBreakdown taxes = taxService.calculateFullTaxYear(215000, true);
        TaxBreakdown taxes = taxService.calculateFullTaxYear(152000.00f, true);

        System.out.println("Federal Income Tax: " + taxes.getFederalIncomeTax());
        System.out.println("State Income Tax: " + taxes.getStateIncomeTax());
        System.out.println("Social Security Tax: " + taxes.getSocialSecurityTax());
        System.out.println("Medicare Tax: " + taxes.getMedicareTax());
        System.out.println("State Disability Insurance Tax: " + taxes.getStateDisabilityInsuranceTax());

        int totalTaxes = taxes.getFederalIncomeTax() +
                         taxes.getStateIncomeTax() +
                         taxes.getSocialSecurityTax() +
                         taxes.getMedicareTax() +
                         taxes.getStateDisabilityInsuranceTax();

        System.out.println("Total tax amount to pay: " + totalTaxes);
        System.out.println("Total taxes to pay each month: " + totalTaxes / 12);
        System.out.println("Total taxes to pay each paycheck: " + totalTaxes / 26);
    }
}
