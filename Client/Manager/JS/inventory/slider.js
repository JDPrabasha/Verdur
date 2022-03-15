function sliderUpdate() {
    $(`[type="range"]`).each(function () {
        var ids = "#" + this.id;
        var b = $(ids);
        var rangePercent = $(`${ids}`).val();
        b.css('filter', 'hue-rotate(-' + rangePercent + 'deg)');
    })

}