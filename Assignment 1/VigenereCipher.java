/**
 * NullCipher
 * Created for CSC115 Assignment One.
 */

public class VigenereCipher implements Cipher {

    private String mKey;

    public VigenereCipher(String key){
        mKey = key;
    }
    
    /**
     * Prints the passed in text followed by the elements of the array
     * separated by commas.
     * @param array The array to be printed.
     * @param text The text to be printed before the array.
     */
    
    private void dumpArray(int[] array, String text){
        System.out.print(text);
        System.out.print(" ");
        for (int k = 0; k < array.length; k++){
            if (k > 0){
                System.out.print(", ");
            }
            System.out.print(array[k]);
        }
        System.out.println();
    }
    
    /**
     * Converts passed in string to an integer array.
     * @param text The text to be printed before the array.
     * @return An integer array created from the passed in string.
     */

    private int[] stringToIntArray(String text){
        int[] arrayFromString = new int[text.length()];

        for (int k = 0; k < text.length(); k++){
            arrayFromString[k] = text.charAt(k) - 97;
        }

        return arrayFromString;
    } /* stringToIntArray */
    
    /**
     * Converts passed in integer array to a string.
     * @param encodedText The array to be converted.
     * @return A string created from the passed in integer array.
     */

    private String intArrayToString(int[] encodedText){
        char[] charArray = new char[encodedText.length];

        for (int k = 0; k < encodedText.length; k++){
            charArray[k] = (char) (encodedText[k] + 97);
        }
        String encodedString = new String(charArray);

        return encodedString;
    }
    
    /**
     * Encrypts passed in plaintext using a key.
     * @param plaintext The text to be encrypted.
     * @return The encrypted plaintext.
     */
    
    public String encrypt(String plaintext){
        //repeat the key to match the length of the plaintext
        int j = 0;
        StringBuilder s = new StringBuilder();
        for (int k = 0; k < plaintext.length(); k++){
            s.append(mKey.charAt(j));
            j++;
            if (j > (mKey.length() - 1)){
                j = 0;
            }
        }
        String expandedKey = s.toString();

        //convert the strings to arrays
        int[] inputArray = stringToIntArray(plaintext);
        int[] keyArray = stringToIntArray(expandedKey);

        //combine arrays and do modulo to encrypt
        int[] combinedArray = new int[plaintext.length()];
        for (int i = 0; i < plaintext.length(); i++){
            combinedArray[i] = ((inputArray[i] + keyArray[i]) % 26);
        }

        //convert encrypted array into string to be returned
        String encryptedString = intArrayToString(combinedArray);

        return encryptedString;
    }
    
    /**
     * Decrypts passed in ciphertext using a key.
     * @param ciphertext The text to be decrypted.
     * @return The decrypted ciphertext.
     */

    public String decrypt(String ciphertext){
        //repeat the key to match the length of the plaintext
        int j = 0;
        StringBuilder s = new StringBuilder();
        for (int k = 0; k < ciphertext.length(); k++){
            s.append(mKey.charAt(j));
            j++;
            if (j > (mKey.length() - 1)){
                j = 0;
            }
        }
        String expandedKey = s.toString();
        
        //convert the strings to arrays
        int[] inputArray = stringToIntArray(ciphertext);
        int[] keyArray = stringToIntArray(expandedKey);
        
        //combine arrays and do modulo to decrypt
        int[] combinedArray = new int[ciphertext.length()];
        for (int i = 0; i < ciphertext.length(); i++){
            combinedArray[i] = ((26 + inputArray[i] - keyArray[i]) % 26);
        }
        
        //convert decrypted array into string to be returned
        String decryptedString = intArrayToString(combinedArray);
        
        return decryptedString;
    }
    
    /**
     * Establishes the key to be used by the Cipher. This
     * version, however, does nothing with the string passed in.
     * @param key A plain text key.
     */
    
    public void setKey(String key){
        mKey = key;
    }
    
}
