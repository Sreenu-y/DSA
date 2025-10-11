class ExamTracker {
    TreeMap<Integer, Long> tracker;
    public ExamTracker() {
        tracker = new TreeMap<>();
    }
    
    public void record(int time, int score) {
        long prevScore = tracker.isEmpty() ? 0 : tracker.lastEntry().getValue();
        tracker.put(time, prevScore + score);
    }
    
    public long totalScore(int startTime, int endTime) {
        Map.Entry<Integer, Long> endEntry = tracker.floorEntry(endTime);
        if(endEntry == null) {
            return 0;
        }

        Map.Entry<Integer, Long> startEntry = tracker.lowerEntry(startTime);
        long res = endEntry.getValue() - (startEntry == null ? 0 : startEntry.getValue());

        return res;
    }
}

/**
 * Your ExamTracker object will be instantiated and called as such:
 * ExamTracker obj = new ExamTracker();
 * obj.record(time,score);
 * long param_2 = obj.totalScore(startTime,endTime);
 */
