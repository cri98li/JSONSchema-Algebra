<<<<<<< HEAD
package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Assertion;

import java.util.List;

public interface WitnessAssertion {
    public WitnessAssertion merge(WitnessAssertion a);

    public WitnessType getGroupType();

    public Assertion getFullAlgebra();
}
=======
package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Assertion;

import java.util.List;

public interface WitnessAssertion {
    public List<WitnessAssertion> merge(WitnessAssertion a);

    public WitnessType getGroupType();

    public Assertion getFullAlgebra();
}
>>>>>>> 9be72e1ac293591d2d50b1d0779180c7b28dedeb
