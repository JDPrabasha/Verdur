function updateExpandButtons() {
    $(`[id^=expand-]`).each(function () {
        var i = this.id.split("-")[1]
        $(this).on("click", function () {
            expand(i)
        })
    })

    $(`[id^=hide-]`).each(function () {
        var i = this.id.split("-")[1]
        $(this).on("click", function () {
            hide(i)
        })
    })
}


function expand(i) {
    document.getElementById(`rec-${i}`).classList.add('hidden');
    document.getElementById(`rec-e-${i}`).classList.remove('hidden');
}
function hide(i) {
    document.getElementById(`rec-${i}`).classList.remove('hidden');
    document.getElementById(`rec-e-${i}`).classList.add('hidden');
}
