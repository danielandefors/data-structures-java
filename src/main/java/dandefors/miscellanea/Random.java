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

    /**
     * Create a new random number generator.
     */
    public Random() {
        this(System.nanoTime());
    }

    /**
     * Create a new random number generator with the given seed value.
     *
     * @param seed The seed.
     */
    public Random(long seed) {
        this.seed = seed;
    }

    /**
     * Generate a pseudo random number with the given number of bits (1-32).
     *
     * @param bits The number of bits.
     * @return The next random number, truncated to the given number of bits.
     */
    public int next(int bits) {
        seed = (seed * 0x5DEECE66DL + 0xBL) & ((1L << 48) - 1);
        return (int) (seed >>> (48 - bits));
    }

}
