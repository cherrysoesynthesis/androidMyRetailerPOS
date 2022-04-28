package com.dcs.myretailer.app.Report;

import android.util.Log;

public class MathUtil {
    /**
     * Truncate smallest decimal value based on given decimal point and roundmode
     * @param	value Input double value to be truncate
     * @param	decimal Decimal point of the <code>value</code> to be truncated
     * @param	roundmode Rounding mode if the <code>value</code> to be truncated by <code>decimal</code> points, the rounding will be based on.
     * <p>
     * <b>Round Mode:</b><br />
     * -1 = no rounding from smallest decimal(Math.floor)<br />
     *  0 = perform 4/5 rounding on smallest decimal(Math.round)<br />
     *  1 = always round up from smallest decimal(Math.ceil)<br />
     * @return	returns truncated double value based on given input
     * <p>
     * <b>Example:</b><br />
     * <table border=1>
     * <tr>
     * <th><code>value</code></th>
     * <th><code>decimal</code></th>
     * <th><code>roundmode</code></th>
     * <th><code>return</code></th>
     * </tr>
     * <tr>
     * <th><code>1.553</code></th>
     * <th><code>2</code></th>
     * <th><code>-1</code></th>
     * <th><code>1.550</code></th>
     * </tr>
     * <tr>
     * <th><code>1.553</code></th>
     * <th><code>2</code></th>
     * <th><code>1</code></th>
     * <th><code>1.560</code></th>
     * </tr>
     * <tr>
     * <th><code>1.553</code></th>
     * <th><code>2</code></th>
     * <th><code>0</code></th>
     * <th><code>1.550</code></th>
     * </tr>
     * <tr>
     * <th><code>0.999</code></th>
     * <th><code>2</code></th>
     * <th><code>-1</code></th>
     * <th><code>0.990</code></th>
     * </tr>
     * <tr>
     * <th><code>0.999</code></th>
     * <th><code>2</code></th>
     * <th><code>1</code></th>
     * <th><code>1.000</code></th>
     * </tr>
     * <tr>
     * <th><code>0.999</code></th>
     * <th><code>2</code></th>
     * <th><code>0</code></th>
     * <th><code>1.000</code></th>
     * </tr>
     * </table>
     **/
    public static double Truncate(double value, int decimal, int roundmode) {
        if (decimal < 0) decimal = 0;
        if (decimal > 9) decimal = 9;
        if(roundmode<-1) roundmode=-1;
        if(roundmode>1)roundmode=1;

        int trunc = 0;
        switch(roundmode){
            case -1:
                trunc = (int) (Math.floor(value * Math.pow(10, decimal)));
                break;
            case 1:
                trunc = (int) (Math.ceil(value * Math.pow(10, decimal)));
                break;
            default:
                trunc = (int) (Math.round(value * Math.pow(10, decimal)));
                break;
        }
        return trunc / Math.pow(10, decimal);
    }


    /**
     * Calculate amount and return rounded value based on given decimal and rounding type function
     * @param	amount Amount input
     * @param	decimal Decimal point of the rounding point to be perform
     * @param	roundtype Rounding type function
     * <p>
     * <b>Round Type:</b><br />
     * 1 = perform swedish rounding(0~2 round to 0, 3~7 round to 5, and 8~9 round to 10)<br />
     * 2 = perform 0/10 rounding(0~4 round to 0, 5~9 round to 10<br />
     * 3 = perform 0/5 rounding(0~4 round to 0, 5~9 round to 5<br />
     * 4 = always round down to 0<br />
     * 5 = always round up to 10<br />
     * any = no rounding perform
     * @return	returns rounded value
     * <p>
     * <b>Example:</b><br />
     * <table border=1>
     * <tr>
     * <th><code>amount</code></th>
     * <th><code>decimal</code></th>
     * <th><code>roundtype</code></th>
     * <th><code>return</code></th>
     * </tr>
     * <tr>
     * <th><code>1.56</code></th>
     * <th><code>2</code></th>
     * <th><code>1</code></th>
     * <th><code>1.60</code></th>
     * </tr>
     * <tr>
     * <th><code>1.52</code></th>
     * <th><code>2</code></th>
     * <th><code>1</code></th>
     * <th><code>1.50</code></th>
     * </tr>
     * </table>
     **/
    public static double CalculateRound(double amount, int decimal, int roundtype) {
        double roundedval = 0d;
        if (decimal > 0) {
            double _rndfig = ((amount * Math.pow(10, decimal)) - ((int) (amount * Math.pow(10, decimal - 1)) * 10));
            int rndfig = (int) Math.round(_rndfig);
            switch (roundtype) {
                case 1: // 0/5/10
                    if (rndfig >= 0 & rndfig <= 2) { // if between 0 and 2 then round down to 0
                        roundedval = ((-rndfig) / Math.pow(10, decimal));
                    } else if (rndfig >= 3 && rndfig <= 7) { // if between 3 and 7 round up/down to 5
                        roundedval = ((5d - rndfig) / Math.pow(10, decimal));
                    } else if (rndfig >= 8 && rndfig <= 9) { // if between 8 and 9 round up to 10
                        roundedval = ((10d - rndfig) / Math.pow(10, decimal));
                    }
                    break;
                case 2: // 0/10
                    if (rndfig >= 0 & rndfig <= 4) { // if between 0 and 4 then round down to 0
                        roundedval = ((-rndfig) / Math.pow(10, decimal));
                    } else if (rndfig >= 5 && rndfig <= 9) { // if between 5 and 9 round up to 10
                        roundedval = ((10d - rndfig) / Math.pow(10, decimal));
                    }
                    break;
                case 3: // 0/5
                    if (rndfig >= 0 & rndfig <= 4) { // if between 0 and 4 then round down to 0
                        roundedval = ((-rndfig) / Math.pow(10, decimal));
                    } else if (rndfig >= 5 && rndfig <= 9) { // if between 5 and 9 round down to 5
                        roundedval = ((5d - rndfig) / Math.pow(10, decimal));
                    }
                    break;
                case 4:// always round down to 0
                    if (rndfig >= 1 & rndfig <= 9) {
                        roundedval = ((-rndfig) / Math.pow(10, decimal));
                    }
                    break;
                case 5:// always round up to 10
                    if (rndfig >= 1 & rndfig <= 9) {
                        roundedval = ((10d - rndfig) / Math.pow(10, decimal));
                    }
                    break;
                default:// no rounding perform
                    break;
            }
        }
        return Truncate(roundedval, decimal, 0);
    }

}

