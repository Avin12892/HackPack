package network_flow;

import java.util.Arrays;

class FordFulkerson {
	int[][] cap;  boolean[] vis;  int n, s, t, oo = (int)(1E9);
	public FordFulkerson(int size) { n = size + 2;  s = n - 2;  t = n - 1;  cap = new int[n][n]; }
	void add(int v1, int v2, int c) {  cap[v1][v2] = c;  }
	int ff() {
		vis = new boolean[n];  int f = 0;
		while (true) {
			Arrays.fill(vis, false);  int res = dfs(s, oo);
			if (res == 0) { break; }  f += res;
		}
		return f;
	}
	int dfs(int pos, int min) {
		if (pos == t)  return min;
		if (vis[pos])  return 0;
		vis[pos] = true;  int f = 0;
		for (int i = 0; i < n; i++) {
			if (cap[pos][i] > 0)  f = dfs(i, Math.min(cap[pos][i], min));
			if (f > 0) { cap[pos][i] -= f;  cap[i][pos] += f;  return f; }
		}
		return 0;
	}
}