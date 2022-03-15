//prints next buttons depending on records
let chunk,array;
export function nextbuttons(x,chunk_get,array_get) {
    array = array_get;
    chunk = chunk_get;
    $("#nextbuttons").html('');
    if (x > chunk) {
        var n = 0;
        while (n < x) {
            n += chunk;
            let buttondiv = $("#nextbuttons"),
                button = $(document.createElement('button')).attr("class", "m-1").html(n / chunk).attr("id", "next-button")
            buttondiv.append(button);

        }
    }
}
