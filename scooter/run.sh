echo ""
echo ""
echo "http://localhost:8787/scooter/index.html?token=`date +%m%d%H%M%S`"
echo ""
echo ""

gradle generateDevHtml jettyRun
