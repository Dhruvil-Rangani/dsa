// leetcode problem: Alien Dictionary
// Problem Link: https://leetcode.com/problems/alien-dictionary/
#include <vector>
#include <queue>
#include <string>
using namespace std;
// Time Complexity: O(N + K)
// Space Complexity: O(N + K)
// N = number of characters in the dictionary, K = number of unique characters
// This code finds the order of characters in an alien language based on the given dictionary.
class Solution {
private:
    vector<int> topoSort(int V, vector<vector<int>> adj) {
        vector<int> inDegree(V, 0);
        for(int i = 0; i < V; i++) {
            for(auto it : adj[i]) {
                inDegree[it]++;
            }
        }

        vector<int> ans;
        queue<int> q;
        for(int i = 0; i < V; i++) {
            if(inDegree[i] == 0) q.push(i);
        }

        while(!q.empty()) {
            int node = q.front();
            ans.push_back(node);
            q.pop();

            for(auto it : adj[node]) {
                inDegree[it]--;
                if(inDegree[it] == 0) q.push(it);
            }
        }

        return ans;
    }
public:
	string findOrder(string dict[], int N, int K) {
		vector<vector<int>> adj(K);
        for(int i = 0; i < N - 1; i++) {
            string s1 = dict[i];
            string s2 = dict[i + 1];
            int len = min(s1.size(), s2.size());

            for(int j = 0; j < len; j++) {
                if(s1[j] != s2[j]) {
                    adj[s1[j] - 'a'].push_back(s2[j] - 'a');
                    break;
                }
            }
        }

        vector<int> topo = topoSort(K, adj);
        string ans;
        for(int i = 0; i < K; i++) {
            ans.push_back('a' + topo[i]);
            ans.push_back(' ');
        }

        return ans;
	}
};

// solution using java language for alien dictionary
// class Solution {
//   /* Function to return the topological
//   sorting of given graph */
//   private List<Integer> topoSort(int V, List<List<Integer>> adj) {

//     // To store the In-degrees of nodes
//     int[] inDegree = new int[V];

//     // Update the in-degrees of nodes
//     for (int i = 0; i < V; i++) {

//       for (int it : adj.get(i)) {
//         // Update the in-degree
//         inDegree[it]++;
//       }
//     }

//     // To store the result
//     List<Integer> ans = new ArrayList<>();

//     // Queue to facilitate BFS
//     Queue<Integer> q = new LinkedList<>();

//     // Add the nodes with no in-degree to queue
//     for (int i = 0; i < V; i++) {
//       if (inDegree[i] == 0) q.add(i);
//     }

//     // Until the queue is empty
//     while (!q.isEmpty()) {

//       // Get the node
//       int node = q.poll();

//       // Add it to the answer
//       ans.add(node);

//       // Traverse the neighbours
//       for (int it : adj.get(node)) {

//         // Decrement the in-degree
//         inDegree[it]--;

//         /* Add the node to queue if
//         its in-degree becomes zero */
//         if (inDegree[it] == 0) q.add(it);
//       }
//     }

//     // Return the result
//     return ans;
//   }

//   /* Function to determine order of
//   letters based on alien dictionary */
//   public String findOrder(String[] dict, int N, int K) {

//     // Initialise a graph of K nodes
//     List<List<Integer>> adj = new ArrayList<>();
//     for (int i = 0; i < K; i++) {
//       adj.add(new ArrayList<>());
//     }

//     // Compare the consecutive words
//     for (int i = 0; i < N - 1; i++) {

//       String s1 = dict[i];
//       String s2 = dict[i + 1];
//       int len = Math.min(s1.length(), s2.length());

//       /* Compare the pair of strings letter by
//       letter to identify the differentiating letter */
//       for (int ptr = 0; ptr < len; ptr++) {

//         // If the differentiating letter is found
//         if (s1.charAt(ptr) != s2.charAt(ptr)) {

//           // Add the edge to the graph
//           adj.get(s1.charAt(ptr) - 'a').add(s2.charAt(ptr) - 'a');
//           break;
//         }
//       }
//     }

//     /* Get the topological sort
//     of the graph formed */
//     List<Integer> topo = topoSort(K, adj);

//     // To store the answer
//     StringBuilder ans = new StringBuilder();

//     for (int i = 0; i < K; i++) {
//       // Add the letter to the result
//       ans.append((char) ('a' + topo.get(i)));
//       ans.append(' ');
//     }

//     // Return the answer
//     return ans.toString();
//   }
// }

