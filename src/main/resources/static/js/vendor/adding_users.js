var current=3;
id=2;
function addline()
{
    var form = document.getElementById('inserting_form');
    var div = document.createElement('div');
    div.setAttribute("align", "center");
    div.setAttribute("class", "form-inline");
    div.innerHTML = '<input style="margin-top: 5px" type="email" name="inputEmail' + current + '" class="form-control" id="exampleInputEmail1" placeholder="email"> <button class="btn btn-danger btn-sm"><span class="glyphicon glyphicon-minus"></span></button>';
    form.insertBefore(div, form.children[current]);
    current++;
    return false;
}