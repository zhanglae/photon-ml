package com.linkedin.photon.ml.test

/**
 * A collection of handy utils useful in tests
 *
 * @author yizhou
 */
object CommonTestUtils {

  /**
   * Append prefix to a CMD line option name, forming an argument string
   *
   * @param optionName the option name
   * @return the argument string
   */
  def fromOptionNameToArg(optionName: String): String = "--" + optionName

  /**
   * This a utility for comparing two constraint maps in unit tests. Returns true if both the options are None or are
   * non-empty and the contained maps have the same set of (key, value) pairs
   *
   * @param m1 option containing the first map
   * @param m2 option containing the second map
   * @return true iff both options are none or contain maps that have the exact same set of (key, value) tuples
   */
  def compareConstraintMaps(m1: Option[Map[Int, (Double, Double)]], m2: Option[Map[Int, (Double, Double)]]): Boolean = {
    (m1, m2) match {
      case (Some(x), Some(y)) =>
        if (x.size != y.size) {
          false
        } else {
          x.foreach{
            case (id, bounds) =>
              y.get(id) match {
                case Some(w: (Double, Double)) =>
                  if (w != bounds) {
                    false
                  }
                case None => false
              }
          }
          true
        }
      case (None, None) => true
      case (None, _) => false
      case (_, None) => false
    }
  }
}