function capicua(number){
    const num_split = number.split('-');
    const country = num_split[0];
    const num = num_split[1];

    // console.log(country)
    // console.log(num)

    num_reverse = num.split('').reverse().join('');
    // console.log(num_reverse)

    return num === num_reverse
}


pattern = function(){ // find capicuas
    // capicua("98-345")

    const phone_numbers = db.phones.find({},{"display": 1, "_id": 0}).toArray();

    const capicuas = []
    for (var i = 0; i < phone_numbers.length; i++) {
        var number = phone_numbers[i].display

        if(capicua(number))
            capicuas.push(number)
    }

    return capicuas
}

// correr no servidor
db.phones.find({ $expr: { $function: { body: function (display) {const num_split = display.split('-'); const country = num_split[0]; const num = num_split[1]; num_reverse = num.split('').reverse().join(''); return num === num_reverse;}, args: ["$display"], lang: "js" } } })