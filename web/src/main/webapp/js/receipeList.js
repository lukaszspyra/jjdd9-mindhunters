$("#myID2").click(function myFunction() {

    let url = new URL(window.location);

    let content = $(event.target).text().trim();

    var searchParams = url.searchParams;

    searchParams.set("page", "1")

    if (!searchParams.getAll("category").includes(content)) {

        searchParams.append("category", content);
        window.location = url;

    } else {

        let searchArray = searchParams.getAll("category");

        searchArray.splice(searchArray.indexOf(content), 1);
        if (searchArray.length >= 1) {
            var newUrl = "&category=" + searchArray.join("&category=");

        } else {
            newUrl = "";
        }

        window.location = '/list?page=1' + newUrl;
    }


})

async function nextPage() {
    let url = new URL(window.location); // or construct from window.location

    let params = new URLSearchParams(url.search.slice(1));

    let pageNumber = parseInt(params.get('page'));

    let newPageNumber = pageNumber + 1;
    params.set('page', (newPageNumber).toString());
    let newURL = 'http://localhost:8080/list?' + params.toString();
    location.replace(newURL);

}

async function previousPage() {
    let url = new URL(window.location); // or construct from window.location

    let params = new URLSearchParams(url.search.slice(1));

    let pageNumber = parseInt(params.get('page'));

    let newPageNumber = pageNumber - 1;
    if (newPageNumber > 0) {
        params.set('page', (newPageNumber).toString());
        let newURL = 'http://localhost:8080/list?' + params.toString();
        location.replace(newURL);

    }
}

async function setPage() {
    let url = new URL(window.location); // or construct from window.location

    let params = new URLSearchParams(url.search.slice(1));

    let pageNumber = parseInt(params.get('page'));

    let newPageNumber = pageNumber - 1;
    if (newPageNumber > 0) {
        params.set('page', (newPageNumber).toString());
        let newURL = 'http://localhost:8080/list?' + params.toString();
        location.replace(newURL);

    }
}

