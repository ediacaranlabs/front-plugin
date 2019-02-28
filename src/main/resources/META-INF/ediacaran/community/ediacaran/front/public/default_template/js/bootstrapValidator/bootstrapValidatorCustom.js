$().validator({
    custom: {
        'name': function($el) { return Boolean($el.val() % 2);}
    },
    errors: {
        name: "Please enter a valid name!"
    }
});