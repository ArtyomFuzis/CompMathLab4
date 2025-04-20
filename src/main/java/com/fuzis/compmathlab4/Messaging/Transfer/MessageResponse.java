package com.fuzis.compmathlab4.Messaging.Transfer;

public record MessageResponse(String Status,
                              String Msg,
                              double[][] Triangle,
                              double Determinant,
                              Boolean DeterminantCalculated,
                              double[] X,
                              double[] Discrepancy,
                              String SolutionsType) {
}
/*type Lab1ReturnDTO struct {
	Status                string
	Msg                   string
	Triangle              Types.Matrix
	Determinant           float64
	DeterminantCalculated bool
	X                     []float64
	Discrepancy           []float64
	SolutionsType         string
}*/