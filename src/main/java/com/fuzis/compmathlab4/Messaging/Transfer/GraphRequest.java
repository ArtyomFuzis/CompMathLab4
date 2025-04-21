package com.fuzis.compmathlab4.Messaging.Transfer;

import com.fuzis.compmathlab4.Math.Approxes;

public record GraphRequest(Long userId, Approxes method, double[] xs, double[] ys, double[] ks)
{

}
