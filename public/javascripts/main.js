$("#foods").load("/dishes?sortBy=");

$("#sort-by").change(function() {
    var sortBy = $("#sort-by option:selected").val();
    $("#foods").load(`/dishes?sortBy=${sortBy}`);
});