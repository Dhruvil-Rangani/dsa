package heaps.hard;
import java.util.*;
// Design a simplified version of Twitter where users can post tweets, follow/unfollow another user and is able to see the 10 most recent tweets in the user's news feed.
// Your design should support the following methods:
// 1. postTweet(userId, tweetId): Compose a new tweet.
// 2. getNewsFeed(userId): Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent.
// 3. follow(followerId, followeeId): Follower follows a followee.
// 4. unfollow(followerId, followeeId): Follower unfollows a followee.
// Time Complexity: O(n) for getNewsFeed where n is the number of users followed by userId
// Space Complexity: O(n + m) where n is the number of users and m is the number of tweets

class Twitter {
    private int cnt;
    private Map<Integer, List<int[]>> tweetMap;
    private Map<Integer, Set<Integer>> followMap;

    public Twitter() {
        this.cnt = 0;
        this.tweetMap = new HashMap<>();
        this.followMap = new HashMap<>();
    }

    public void postTweet(int userId, int tweetId) {
        tweetMap.computeIfAbsent(userId, k -> new ArrayList<>()).add(new int[]{cnt, tweetId});
        if(tweetMap.get(userId).size() > 10) tweetMap.get(userId).remove(0);
        cnt--;
    }

    public List<Integer> getNewsFeed(int userId) {
        List<Integer> res = new ArrayList<>();
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(
            (a, b) -> Integer.compare(a[0], b[0])
        );
        followMap.computeIfAbsent(userId, k -> new HashSet<>()).add(userId);
        if(followMap.get(userId).size() >= 10) {
             PriorityQueue<int[]> maxHeap = new PriorityQueue<>(
                (a, b) -> Integer.compare(a[0], b[0])
            );
            for(int followeeId : followMap.get(userId)) {
                if(!tweetMap.containsKey(followeeId)) continue;
                List<int[]> tweets = tweetMap.get(followeeId);
                int index = tweets.size() - 1;
                int[] tweet = tweets.get(index);
                maxHeap.offer(new int[]{-tweet[0], tweet[1], followeeId, index - 1});
                if (maxHeap.size() > 10) {
                    maxHeap.poll();
                }
            }
            while(!maxHeap.isEmpty()) {
                int[] top = maxHeap.poll();
                minHeap.offer(new int[]{-top[0], top[1], top[2], top[3]});
            }
        } else {
            for(int followeeId : followMap.get(userId)) {
                if (!tweetMap.containsKey(followeeId)) continue;
                List<int[]> tweets = tweetMap.get(followeeId);
                int index = tweets.size() - 1;
                int[] tweet = tweets.get(index);
                minHeap.offer(new int[]{tweet[0], tweet[1], followeeId, index - 1});
            }
        }

        while(!minHeap.isEmpty() && res.size() < 10) {
            int[] top = minHeap.poll();
            res.add(top[1]);
            int nextIndex = top[3];
            if(nextIndex >= 0) {
                List<int[]> tweets = tweetMap.get(top[2]);
                int[] nextTweet = tweets.get(nextIndex);
                minHeap.offer(new int[]{nextTweet[0], nextTweet[1], top[2], nextIndex - 1});
            }
        }
        return res;
    }

    public void follow(int followerId, int followeeId) {
        followMap.computeIfAbsent(followerId, k -> new HashSet<>()).add(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {
        followMap.computeIfPresent(followerId, (key, set) -> {
            set.remove(followeeId);
            return set.isEmpty() ? null : set;
        });
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */