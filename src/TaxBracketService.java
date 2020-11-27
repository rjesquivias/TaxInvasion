abstract public class TaxBracketService {

    abstract public TaxBreakdown calculateFullTaxYear(float grossIncome, boolean isMarried);

    abstract public TaxBreakdown calculateRemainingTaxYear(float grossIncome, boolean isMarried, TaxBreakdown paidTaxes);
}
