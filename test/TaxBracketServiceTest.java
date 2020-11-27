import org.junit.Assert;
import org.junit.Test;
public class TaxBracketServiceTest {
    @Test
    public void calculateFullTaxYearTest()
    {
        // $150,000 - $24,800(standard deduction) = $125,200 (taxable income)
        // $9,235 plus 22% of the amount over $80,250
        // 44,950 * .22 = $9,889
        // Total Federal Income Tax = $9,235 + $9,889 = $19,124
        TaxBracketService taxService = new TaxBracketService2020();
        TaxBreakdown taxBreakdown = taxService.calculateFullTaxYear(150000.00f, true);
        Assert.assertNotNull(taxBreakdown);
        Assert.assertEquals(19124, taxBreakdown.getFederalIncomeTax());
        Assert.assertEquals(5980, taxBreakdown.getStateIncomeTax());
        Assert.assertEquals(7762, taxBreakdown.getSocialSecurityTax());
        Assert.assertEquals(1815, taxBreakdown.getMedicareTax());
        Assert.assertEquals(1252, taxBreakdown.getStateDisabilityInsuranceTax());

        // $1,000,000 - $24,800(standard deduction) = $975,200 (taxable income)
        // $167,307.50 plus 37% of the amount over $622,050
        // 353,150 * .37 = $130,665.50
        // Total Federal Income Tax = $167,307.50 + $130,665.50= $297,973.00
        taxBreakdown = taxService.calculateFullTaxYear(1000000.00f, true);
        Assert.assertNotNull(taxBreakdown);
        Assert.assertEquals(297973, taxBreakdown.getFederalIncomeTax());
        Assert.assertEquals(91538, taxBreakdown.getStateIncomeTax());
        Assert.assertEquals(60462, taxBreakdown.getSocialSecurityTax());
        Assert.assertEquals(14140, taxBreakdown.getMedicareTax());
        Assert.assertEquals(9752, taxBreakdown.getStateDisabilityInsuranceTax());

        // 1,275,200 taxable income
        taxBreakdown = taxService.calculateFullTaxYear(1300000.00f, true);
        Assert.assertNotNull(taxBreakdown);
        Assert.assertEquals(408973, taxBreakdown.getFederalIncomeTax());
        Assert.assertEquals(126375, taxBreakdown.getStateIncomeTax());
        Assert.assertEquals(79062, taxBreakdown.getSocialSecurityTax());
        Assert.assertEquals(18490, taxBreakdown.getMedicareTax());
        Assert.assertEquals(12752, taxBreakdown.getStateDisabilityInsuranceTax());

        // $27,450.75 - $24,800(standard deduction) = $2,650.75 (taxable income)
        // $2,650.75 * .10 = $265.075
        // Total Federal Income Tax = $265.075 -> (round) 265
        taxBreakdown = taxService.calculateFullTaxYear(27450.75f, true);
        Assert.assertNotNull(taxBreakdown);
        Assert.assertEquals(265, taxBreakdown.getFederalIncomeTax());
        Assert.assertEquals(27, taxBreakdown.getStateIncomeTax());
        Assert.assertEquals(164, taxBreakdown.getSocialSecurityTax());
        Assert.assertEquals(38, taxBreakdown.getMedicareTax());
        Assert.assertEquals(27, taxBreakdown.getStateDisabilityInsuranceTax());

        // $49,457.55 - $24,800(standard deduction) = $24,657.55 (taxable income)
        // $1,975 plus 12% of the amount over $19,750
        // Total Federal Income Tax = $588.906 + $1,975 = 2563.906 -> (round) 2564
        taxBreakdown = taxService.calculateFullTaxYear(49457.55f, true);
        Assert.assertNotNull(taxBreakdown);
        Assert.assertEquals(2564, taxBreakdown.getFederalIncomeTax());
        Assert.assertEquals(317, taxBreakdown.getStateIncomeTax());
        Assert.assertEquals(1529, taxBreakdown.getSocialSecurityTax());
        Assert.assertEquals(358, taxBreakdown.getMedicareTax());
        Assert.assertEquals(247, taxBreakdown.getStateDisabilityInsuranceTax());

        // $19,740.00f - $24,800(standard deduction) = $0 (taxable income)
        // Total Federal Income Tax = $0
        taxBreakdown = taxService.calculateFullTaxYear(19740.00f, true);
        Assert.assertNotNull(taxBreakdown);
        Assert.assertEquals(0, taxBreakdown.getFederalIncomeTax());
        Assert.assertEquals(0, taxBreakdown.getStateIncomeTax());
        Assert.assertEquals(0, taxBreakdown.getSocialSecurityTax());
        Assert.assertEquals(0, taxBreakdown.getMedicareTax());
        Assert.assertEquals(0, taxBreakdown.getStateDisabilityInsuranceTax());

        // $24,800 - $24,800(standard deduction) = $0 (taxable income)
        // Total Federal Income Tax = $0
        taxBreakdown = taxService.calculateFullTaxYear(24800.00f, true);
        Assert.assertNotNull(taxBreakdown);
        Assert.assertEquals(0, taxBreakdown.getFederalIncomeTax());
        Assert.assertEquals(0, taxBreakdown.getStateIncomeTax());
        Assert.assertEquals(0, taxBreakdown.getSocialSecurityTax());
        Assert.assertEquals(0, taxBreakdown.getMedicareTax());
        Assert.assertEquals(0, taxBreakdown.getStateDisabilityInsuranceTax());

        taxBreakdown = taxService.calculateFullTaxYear(-30000.00f, true);
        Assert.assertNotNull(taxBreakdown);
        Assert.assertEquals(0, taxBreakdown.getFederalIncomeTax());
        Assert.assertEquals(0, taxBreakdown.getStateIncomeTax());
        Assert.assertEquals(0, taxBreakdown.getSocialSecurityTax());
        Assert.assertEquals(0, taxBreakdown.getMedicareTax());
        Assert.assertEquals(0, taxBreakdown.getStateDisabilityInsuranceTax());
    }
}
