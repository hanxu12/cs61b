import java.util.HashMap;
import java.util.Map;

/**
 * This class provides all code necessary to take a query box and produce
 * a query result. The getMapRaster method must return a Map containing all
 * seven of the required fields, otherwise the front end code will probably
 * not draw the output correctly.
 */
public class Rasterer {
    public static final double ROOT_ULLAT = 37.892195547244356, ROOT_ULLON = -122.2998046875,
            ROOT_LRLAT = 37.82280243352756, ROOT_LRLON = -122.2119140625;
    /** Each tile is 256x256 pixels. */
    public static final int TILE_SIZE = 256;

    public Rasterer() {
        // YOUR CODE HERE

    }

    /**
     * Takes a user query and finds the grid of images that best matches the query. These
     * images will be combined into one big image (rastered) by the front end. <br>
     *
     *     The grid of images must obey the following properties, where image in the
     *     grid is referred to as a "tile".
     *     <ul>
     *         <li>The tiles collected must cover the most longitudinal distance per pixel
     *         (lonDPP) possible, while still covering less than or equal to the amount of
     *         longitudinal distance per pixel in the query box for the user viewport size. </li>
     *         <li>Contains all tiles that intersect the query bounding box that fulfill the
     *         above condition.</li>
     *         <li>The tiles must be arranged in-order to reconstruct the full image.</li>
     *     </ul>
     *
     * @param params Map of the HTTP GET request's query parameters - the query box and
     *               the user viewport width and height.
     *
     * @return A map of results for the front end as specified: <br>
     * "render_grid"   : String[][], the files to display. <br>
     * "raster_ul_lon" : Number, the bounding upper left longitude of the rastered image. <br>
     * "raster_ul_lat" : Number, the bounding upper left latitude of the rastered image. <br>
     * "raster_lr_lon" : Number, the bounding lower right longitude of the rastered image. <br>
     * "raster_lr_lat" : Number, the bounding lower right latitude of the rastered image. <br>
     * "depth"         : Number, the depth of the nodes of the rastered image <br>
     * "query_success" : Boolean, whether the query was able to successfully complete; don't
     *                    forget to set this to true on success! <br>
     */
    public Map<String, Object> getMapRaster(Map<String, Double> params) {
        Map<String, Double> input = params;
        Double lrlonIn = params.get("lrlon");
        Double ullonIn = params.get("ullon");
        Double ullatIn = params.get("ullat");
        Double lrlatIn = params.get("lrlat");
        Double widthIn = params.get("w");
        Double heightIn = params.get("h");
        Double lonDPP = (lrlonIn - ullonIn) / widthIn;
        int depth = 0;
        Double rootLonDPP = (ROOT_LRLON - ROOT_ULLON) / TILE_SIZE;
        while (rootLonDPP > lonDPP) {
            rootLonDPP = rootLonDPP / 2;
            depth += 1;
            if (depth == 7) {
                break;
            }
        }
        //determining x
        int leftX = 0;
        int rightX = (int) (Math.pow(2, depth)) - 1;
        Double lonDivider = (ROOT_LRLON - ROOT_ULLON) / (Math.pow(2, depth));
        Double tempUlLon = ROOT_ULLON;
        while (tempUlLon < ullonIn) {
            if (tempUlLon + lonDivider > ullonIn) {
                break;
            }
            tempUlLon += lonDivider;
            leftX += 1;
        }
        Double tempLrLon = ROOT_LRLON;
        while (tempLrLon > lrlonIn) {
            if (tempLrLon - lonDivider < lrlonIn) {
                break;
            }
            tempLrLon -= lonDivider;
            rightX -= 1;
        }
        //determining y
        int topY = 0;
        int btmY = (int) (Math.pow(2, depth)) - 1;
        Double latDivider = (ROOT_ULLAT - ROOT_LRLAT) / (Math.pow(2, depth));
        Double tempUlLat = ROOT_ULLAT;
        while (tempUlLat > ullatIn) {
            if (tempUlLat - latDivider < ullatIn) {
                break;
            }
            tempUlLat -= latDivider;
            topY += 1;
        }
        Double tempLrLat = ROOT_LRLAT;
        while (tempLrLat < lrlatIn) {
            if (tempLrLat + latDivider > lrlatIn) {
                break;
            }
            tempLrLat += latDivider;
            btmY -= 1;
        }
        String[][] renderGrid = new String[btmY - topY + 1][rightX - leftX + 1];
        for (int i = topY; i <= btmY; i++) {
            for (int j = leftX; j <= rightX; j++) {
                String temp = "d" + depth + "_x" + j + "_y" + i + ".png";
                renderGrid[i - topY][j - leftX] = temp;
            }
        }
        System.out.println(params);
        Map<String, Object> results = new HashMap<>();
        results.put("render_grid", renderGrid);
        results.put("depth", depth);
        results.put("raster_ul_lon", tempUlLon);
        results.put("raster_lr_lon", tempLrLon);
        results.put("raster_ul_lat", tempUlLat);
        results.put("raster_lr_lat", tempLrLat);
        results.put("query_success", true);

        System.out.println(results);
//        System.out.println("Since you haven't implemented getMapRaster, nothing is displayed in "
//                           + "your browser.");
        return results;
    }

}
