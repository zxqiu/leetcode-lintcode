/*

Mini Twitter

Implement a simple twitter. Support the following method:

postTweet(user_id, tweet_text). Post a tweet.
getTimeline(user_id). Get the given user's most recently 10 tweets posted by himself, order by timestamp from most recent to least recent.
getNewsFeed(user_id). Get the given user's most recently 10 tweets in his news feed (posted by his friends and himself). Order by timestamp from most recent to least recent.
follow(from_user_id, to_user_id). from_user_id followed to_user_id.
unfollow(from_user_id, to_user_id). from_user_id unfollowed to to_user_id.


Example
postTweet(1, "LintCode is Good!!!")
>> 1
getNewsFeed(1)
>> [1]
getTimeline(1)
>> [1]
follow(2, 1)
getNewsFeed(2)
>> [1]
unfollow(2, 1)
getNewsFeed(2)
>> []

*/




/**
 * Definition of Tweet:
 * public class Tweet {
 *     public int id;
 *     public int user_id;
 *     public String text;
 *     public static Tweet create(int user_id, String tweet_text) {
 *         // This will create a new tweet object,
 *         // and auto fill id
 *     }
 * }
 */
 
/**
 * Scenario:
 * ignore QPS
 * 
 * Service:
 * user service
 * friendship service
 * tweet service
 * 
 * Storage:
 * select physical structure : ignore
 * schema:
 *  User: id, ignore all other fields...
 *  Friendship: id, from_user_id, to_user_id
 *	Tweet: id, text, user_id
 * 
 * 
 * Scale:
 * ignore
 * 
 * Solution:
 * pull
*/
public class MiniTwitter {
    // User
    private class User {
        public int id;
        public User(int id) {
            this.id = id;
        }
    }
    
    // Friendship
    private class Friendship {
        public int id;
        public int from_user_id;
        public int to_user_id;
        public Friendship(int id, int from_user_id, int to_user_id) {
            this.id = id;
            this.from_user_id = from_user_id;
            this.to_user_id = to_user_id;
        }
    }
    
    // Tables
    private ArrayList<User> userTable;
    private ArrayList<Friendship> friendshipTable;
    private ArrayList<Tweet> tweetTable;
    
    private static int friendshipId = 0;
    
    public MiniTwitter() {
        userTable = new ArrayList<User>();
        friendshipTable = new ArrayList<Friendship>();
        tweetTable = new ArrayList<Tweet>();
    }

    // @param user_id an integer
    // @param tweet a string
    // return a tweet
    public Tweet postTweet(int user_id, String tweet_text) {
        Tweet tweet = Tweet.create(user_id, tweet_text);
        tweetTable.add(0, tweet);
        return tweet;
    }

    // @param user_id an integer
    // return a list of 10 new feeds recently
    // and sort by timeline
    public List<Tweet> getNewsFeed(int user_id) {
        // 1. find all followings
        Set<Integer> followings = new HashSet<Integer>();
        for (Friendship f : friendshipTable) {
            if (f.from_user_id == user_id) {
                followings.add(f.to_user_id);
            }
        }
        
        // 2. go through tweet table
        List<Tweet> ret = new ArrayList<Tweet>();
        for (Tweet t : tweetTable) {
            if (t.user_id == user_id ||
                followings.contains(t.user_id)) {
                ret.add(t);
                if (ret.size() == 10) {
                    break;
                }
            }
        }
        
        return ret;
    }
        
    // @param user_id an integer
    // return a list of 10 new posts recently
    // and sort by timeline
    public List<Tweet>  getTimeline(int user_id) {
        List<Tweet> ret = new ArrayList<Tweet>();
        for (Tweet t : tweetTable) {
            if (t.user_id == user_id) {
                ret.add(t);
                if (ret.size() == 10) {
                    break;
                }
            }
        }
        
        return ret;
    }

    // @param from_user_id an integer
    // @param to_user_id an integer
    // from user_id follows to_user_id
    public void follow(int from_user_id, int to_user_id) {
        for (Friendship f : friendshipTable) {
            if (f.from_user_id == from_user_id &&
                f.to_user_id == to_user_id) {
                return;
            }
        }
        
        friendshipTable.add(new Friendship(friendshipId++, from_user_id, to_user_id));
    }

    // @param from_user_id an integer
    // @param to_user_id an integer
    // from user_id unfollows to_user_id
    public void unfollow(int from_user_id, int to_user_id) {
        for (int i = 0; i < friendshipTable.size(); i++) {
            Friendship f = friendshipTable.get(i);
            if (f.from_user_id == from_user_id &&
                f.to_user_id == to_user_id) {
                friendshipTable.remove(i);
                return;
            }
        }
    }
}