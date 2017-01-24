/*

Friendship Service

Support follow & unfollow, getFollowers, getFollowings.

follow(1, 3)
getFollowers(1) // return [3]
getFollowings(3) // return [1]
follow(2, 3)
getFollowings(3) // return [1,2]
unfollow(1, 3)
getFollowings(3) // return [2]


解：
单向好友关系。
使用两个HashMap分别保存follower和following，以提高查找效率。
在每个HashMap内，使用TreeSet来保存内容，利用TreeSet自排序特性，保证查找的结果有序。
同时，可以避免在插入时查重。

*/



public class FriendshipService { 

    HashMap<Integer, Set<Integer>> followers, followings;

    public FriendshipService() {
        followers = new HashMap<Integer, Set<Integer>>();
        followings = new HashMap<Integer, Set<Integer>>();
    }

    // @param user_id an integer
    // return all followers and sort by user_id
    public List<Integer> getFollowers(int user_id) {
        if (!followers.containsKey(user_id)) {
            return new ArrayList<Integer>();
        }
        
        return new ArrayList<Integer>(followers.get(user_id));
    }
        
    // @param user_id an integer
    // return all followings and sort by user_id
    public List<Integer>  getFollowings(int user_id) {
        if (!followings.containsKey(user_id)) {
            return new ArrayList<Integer>();
        }
        
        return new ArrayList<Integer>(followings.get(user_id));
    }

    // @param from_user_id an integer
    // @param to_user_id an integer
    // from user_id follows to_user_id
    public void follow(int from_user_id, int to_user_id) {
        if (!followers.containsKey(from_user_id)) {
            followers.put(from_user_id, new TreeSet<Integer>());
        }
        
        followers.get(from_user_id).add(to_user_id);
        
        if (!followings.containsKey(to_user_id)) {
            followings.put(to_user_id, new TreeSet<Integer>());
        }
        
        followings.get(to_user_id).add(from_user_id);
    }

    // @param from_user_id an integer
    // @param to_user_id an integer
    // from user_id unfollows to_user_id
    public void unfollow(int from_user_id, int to_user_id) {
        if (!followers.containsKey(from_user_id)) {
            return;
        }
        
        followers.get(from_user_id).remove(to_user_id);
        
        if (!followings.containsKey(to_user_id)) {
            return;
        }
        
        followings.get(to_user_id).remove(from_user_id);
    }
}