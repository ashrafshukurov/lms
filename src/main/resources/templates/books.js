$(function() {
        $.ajax({
            url: "url",
            type: "get",
            dataType: "json",
            success: function(data) {
             console.log(data.name);
                for (i = 0; data.length; i++)  {
                    name = data[i].name;
                    web_pages = data[i].web_pages;

                    var link = document.createElement('a');
                    link.setAttribute('href', web_pages);
                    link.innerHTML = web_pages;

                      var paragraph = $("<p />", { text: name + " " }).append(link)

                    $("#display-resources").append(paragraph);
                }
            }
        });
    });