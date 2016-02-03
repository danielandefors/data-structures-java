package dandefors.miscellanea;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Job selection algorithm.
 */
public class JobSelection {

    /**
     * A job with a start time and end time (here expressed as integers).
     */
    public static class Job {

        private int start;
        private int end;

        public Job(int start, int end) {
            this.start = start;
            this.end = end;
        }

        /**
         * Get the start time.
         *
         * @return The start time.
         */
        public int getStart() {
            return start;
        }

        /**
         * Get the end time.
         *
         * @return The end time.
         */
        public int getEnd() {
            return end;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Job job = (Job) o;
            return start == job.start && end == job.end;
        }

    }

    /**
     * Select the maximum number of non-overlapping jobs.
     *
     * @param jobs Some jobs.
     * @return The optimal selection.
     */
    public Job[] select(Job... jobs) {

        // Sort jobs by their end time in ascending order
        Job[] j = jobs.clone();
        Arrays.sort(j, ((o1, o2) -> o1.getEnd() - o2.getEnd()));

        List<Job> s = new ArrayList<>(j.length);

        // Select the first job, since it finishes first of all
        s.add(j[0]);
        int end = j[0].getEnd();

        // Continue to select as many jobs as possible
        for (int i = 1; i < j.length; i++) {
            Job job = j[i];
            // Make sure that the next job we select doesn't overlap with previously selected jobs
            if (job.getStart() >= end) {
                s.add(job);
                end = job.getEnd();
            }
        }

        return s.toArray(new Job[s.size()]);

    }

}
