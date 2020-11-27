import java.util.LinkedHashMap;
import java.util.Map;

public class TaxBracketService2020 extends TaxBracketService {

    private final LinkedHashMap<Float, Integer> federalTaxBracket = new LinkedHashMap<>();
    private final LinkedHashMap<Float, Integer> californiaTaxBracket = new LinkedHashMap<>();

    TaxBracketService2020() {
        federalTaxBracket.put(0.10f, 19750);
        federalTaxBracket.put(0.12f, 80250);
        federalTaxBracket.put(0.22f, 171050);
        federalTaxBracket.put(0.24f, 326600);
        federalTaxBracket.put(0.32f, 414700);
        federalTaxBracket.put(0.35f, 622050);
        federalTaxBracket.put(0.37f, 0);

        californiaTaxBracket.put(0.01f, 17618);
        californiaTaxBracket.put(0.02f, 41766);
        californiaTaxBracket.put(0.04f, 65920);
        californiaTaxBracket.put(0.06f, 91506);
        californiaTaxBracket.put(0.08f, 115648);
        californiaTaxBracket.put(0.093f, 590746);
        californiaTaxBracket.put(0.103f, 708890);
        californiaTaxBracket.put(0.113f, 1181484);
        californiaTaxBracket.put(0.123f, 0);
    }

    @Override
    public TaxBreakdown calculateFullTaxYear(float grossIncome, boolean isMarried) {
        final float socialSecurityTaxRate = 0.062f;
        final float medicareTaxRate = 0.0145f;
        final float stateDisabilityTaxRate = 0.01f;

        float taxableIncome = isMarried ? grossIncome - 24800 : grossIncome - 12400;

        if(taxableIncome <= 0.0f)
        {
            return new TaxBreakdown(0, 0, 0, 0, 0);
        }

        int federalIncomeTaxToPay = roundOff(applyTaxBracket(taxableIncome, federalTaxBracket));
        int stateIncomeTaxToPay   = roundOff(applyTaxBracket(taxableIncome, californiaTaxBracket));
        int socialSecurityTax     = roundOff(taxableIncome * socialSecurityTaxRate);
        int medicareTax           = roundOff(taxableIncome * medicareTaxRate);
        int stateDisabilityTax    = roundOff(taxableIncome * stateDisabilityTaxRate);

        return new TaxBreakdown(federalIncomeTaxToPay, stateIncomeTaxToPay, socialSecurityTax, medicareTax, stateDisabilityTax);
    }

    @Override
    public TaxBreakdown calculateRemainingTaxYear(float grossIncome, boolean isMarried, TaxBreakdown paidTaxes) {
        return null;
    }

    private int roundOff(float dollarAmount)
    {
        // amounts under 50 cents round down ($97.25 becomes $97)
        // amounts from 50 to 99 cents round up ($97.76 becomes $98)
        return dollarAmount - (int)dollarAmount >= 0.50f ? (int)dollarAmount + 1 : (int)dollarAmount;
    }

    private float applyTaxBracket(float taxableIncome, LinkedHashMap<Float, Integer> taxBracket)
    {
        float taxedSoFar = 0.0f;
        float totalTaxesOwed = 0.0f;

        for(Map.Entry<Float, Integer> entry : taxBracket.entrySet())
        {
            float taxRate = entry.getKey();
            int taxableIncomeLimit = entry.getValue();

            if(taxableIncome > taxedSoFar)
            {
                float amount = (taxableIncome > taxableIncomeLimit && taxableIncomeLimit != 0) ? taxableIncomeLimit - taxedSoFar : taxableIncome - taxedSoFar;
                totalTaxesOwed += amount * taxRate;
                taxedSoFar += amount;
            }
        }

        return totalTaxesOwed;
    }
}
