package dandefors.miscellanea;

/**
 * Linear congruential generator.
 */
public class Random {

    /*
    https://en.wikipedia.org/wiki/Linear_congruential_generator
    http://lemire.me/blog/2016/02/01/default-random-number-generators-are-slow/
     */

    private long seed;

    public Random(long seed) {
        this.seed = seed;
    }

    public int next(int bits) {
        seed = (seed * 0x5DEECE66DL + 0xBL) & ((1L << 48) - 1);
        return (int) (seed >>> (48 - bits));
    }

}
