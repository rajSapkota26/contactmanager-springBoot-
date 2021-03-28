console.log("this is my java script")
const toggleSidebar = () => {
    if ($('.sidebar').is(":visible")) {
        $(".sidebar").css("display", "none");
        $(".content").css("margin-left", "0%")
    } else {
        $(".sidebar").css("display", "block");
        $(".content").css("margin-left", "25%")
    }
};
const search = () => {
    let query = $("#search_input").val();

    if (query == '') {
        $(".search_result").hide();
    } else {
        let url = `http://localhost:8989/search/${query}`;
        fetch(url).then(Response => {
            return Response.json();
        }).then(data => {
            let text = `<div class='list-group'>`;
            data.forEach((contact) => {
                text += `<a href='/user/contact/${contact.cId}' class='list-group-item list-group-action'>${contact.name}</a>`;
            });
            text += `</div>`;
            $(".search_result").html(text);
            $(".search_result").show();
        });

    }
}
