/**
 * This class represents a credit card payment.
 * It holds the card number as an int[].
 * It verifies three properties (in this order):
 * 1. That the int[] only holds ints in the following range: [0,9]
 * 2. That the length of the int[] is exactly 6
 * 3. That the check digit is correct
 *
 * The check digit is the last digit of the card number that verifies
 * that the other 5 digits are correct. This number is computed with
 * the Luhn algorithm (https://en.wikipedia.org/wiki/Luhn_algorithm).
 */
public class CreditCard extends Payment {
    private final int[] cardNumber;

    public CreditCard(double amount, int[] cardNumber) throws IllegalArgumentException {
        super(amount);
        this.cardNumber = cardNumber;

        // TODO: Perform the following verification:
        //       1. Use verifyCardDigits to check that all ints in card number
        //          are in the following range: [0,9]. If not then throw an IllegalArgumentException
        //          with the following String: "The card number must consist of numbers in the following range: [0,9]"
        //       2. Check that the card number length is exactly 6. If not then throw an IllegalArgumentException
        //          with the following String: "The card number must be exactly 6 digits"
        //       3. Use verify to check that the check digit is correct in the card number. If it is not VALID then
        //          throw an IllegalArgumentException with the toString of verification.


        if( (cardNumber.length> 6) || (cardNumber.length< 6) ){
            throw new IllegalArgumentException("The card number must be exactly 6 digits");
        }
         else if (!verifyCardDigits()){
            throw new IllegalArgumentException("The card number must consist of numbers in the following range: [0,9]");
        }

        else if (verify()!= PaymentVerification.VALID){
            throw  new IllegalArgumentException(PaymentVerification.INVALIDCARDNUMBER.toString());
        }
    }


    /**
     * This function verifies that the cardNumber only has
     * ints in the following range: [0,9].
     * @return True if all ints are in the above range false otherwise.
     */
    private boolean verifyCardDigits() {
        //TODO: Fill in the logic given above, replace return false with your code.
        for ( int x: cardNumber){
             if (( x< 0)|| (x>9)){
                 return false;
             }
         }
         return true;
    }

    /**
     * This function sums the digits of the given int.
     * For example if given 123 it would return 6.
     * @param num Int to sum the digits of
     * @return Sum of the digits of the given int
     */
    private int sumDigits(int num) {
        //TODO: Fill in the logic given above, replace return 0 with your code.

        int x;
        int sum = 0;
        while ( num > 0){
            x = ( num% 10);
            num = num/10;
            sum += x;
        }
        return sum;
    }

    /**
     * This function verifies that cardNumber's check
     * digit is correct using the Luhn algorithm.
     * @return VALID if the check digit is correct, INVALIDCARDNUMBER otherwise
     */
    @Override
    protected PaymentVerification verify() {
        //TODO: Fill in the logic given above, replace return null with your code.

        int sum1 =0;
        sum1 = cardNumber[1]+ cardNumber[3];

        int sum2 = 0;
        int oddcard1 = cardNumber[0]*2;
        if ( oddcard1 > 9){
         oddcard1= sumDigits(oddcard1);

      }
      int oddcard2 = cardNumber[2]*2;
      if ( oddcard2 > 9){
          oddcard2 =sumDigits(oddcard2);
      }
      int oddcard3 = cardNumber[4]*2;
      if ( oddcard3 > 9){
            oddcard3 = sumDigits(oddcard3);
      }
      sum2 = sum1+oddcard1+oddcard2+oddcard3;
      int checker = ( (10-(sum2%10)) % 10);
      if ( checker == cardNumber[cardNumber.length-1]){
          return PaymentVerification.VALID;
      }
      return PaymentVerification.INVALIDCARDNUMBER;
    }

    /**
     * This function checks if the cardNumber of this matches the card
     * number of o.
     * @param o Other CreditCard to check if equal to this
     * @return True if o's cardNumber is equal to this.cardNumber
     */
    @Override
    public boolean equals(Object o) {
        //TODO: Fill in the logic given above, replace return false with your code.
        if ( o.equals(this.cardNumber)){
            return true;
        }
        return false;
    }

    /**
     * This function converts this to a string
     * @return "Valid Credit Card Number: $CARDNUMBER, amount: {@link #amount}"
     */
    @Override
    public String toString() {
        //TODO: Fill in the logic given above, replace return null with your code.
        int j = 0;
        for ( int i =0; i< cardNumber.length; i++){
            j= j*10+cardNumber[i];
        }
        return ("Valid Credit Card Number: "+j+ ", amount: "+amount);
    }
}
