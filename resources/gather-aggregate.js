fromCategory('Customer')
    .foreachStream().when(
        {
            $any : function(s,e) {
                linkTo("Customer", e);
            }
        })