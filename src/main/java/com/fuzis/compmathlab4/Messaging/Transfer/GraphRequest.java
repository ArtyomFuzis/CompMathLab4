package com.fuzis.compmathlab4.Messaging.Transfer;

import com.fuzis.compmathlab4.MathLAB4.Approxes;

public record GraphRequest(Long userId, Approxes method, double[] xs, double[] ys, double[] ks)
{

}
