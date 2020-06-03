package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness;

public class Utils_Witness {
    /**
     * Usato durante la fase di variableNormalization_separation per stabilire in modo univoco (e sensato) il nome di una nuova variabile
     * @param assertion corpo della nuova variabile per cui dobbiamo decidere il nome
     * @return ritorna un nome composto da nomeVariabile_HASHCODE (sfruttiamo la proprietà di unicità dell'hashcode)
     */
    protected static String getName(WitnessAssertion assertion){
        return assertion.getClass().getSimpleName()+ "_" +assertion.hashCode();
    }
}
