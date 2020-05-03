package it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.Witness;

import it.unipi.di.tesiFalleniLandi.JsonSchema_to_Algebra.FullAlgebra.Assertion;

import java.util.List;

public interface WitnessAssertion {
    public List<WitnessAssertion> merge(WitnessAssertion a);

    public WitnessType getGroupType();

    public Assertion getFullAlgebra();
}
