package array_string;

public class CompareVersionNumbers {
    public static void test() {
        new CompareVersionNumbers().compareVersion("1.0.1", "1");
    }

    public int compareVersion(String version1, String version2) {
        int[] v1 = getVersions(version1);
        int[] v2 = getVersions(version2);
        for (int i = 0; i < Math.max(v1.length, v2.length); ++i) {
            int first = i < v1.length ? v1[i] : 0;
            int second = i < v2.length ? v2[i] : 0;
            if (first > second) {
                return 1;
            } else if (first < second) {
                return -1;
            }
        }
        return 0;
    }

    private int[] getVersions(String version) {
        String[] vStr = version.split("\\.");
        int[] v = new int[vStr.length];
        for (int i = 0; i < vStr.length; ++i) {
            v[i] = Integer.parseInt(vStr[i]);
        }
        return v;
    }

    public int compareVersion2(String version1, String version2) {
        int[] v1 = getVersions(version1);
        int[] v2 = getVersions(version2);
        int i = 0;
        for (; i < Math.min(v1.length, v2.length); ++i) {
            if (v1[i] > v2[i]) {
                return 1;
            } else if (v1[i] < v2[i]) {
                return -1;
            }
        }
        if (v1.length > v2.length) {
            while (i < v1.length) {
                if (v1[i++] > 0) return 1;
            }
        } else {
            while (i < v2.length) {
                if (v2[i++] > 0) return -1;
            }
        }
        return 0;
    }
}
