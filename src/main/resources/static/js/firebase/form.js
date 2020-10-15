function teste(){
    const flag = [[${sucesso}]];
    window.onload = function() {
        if(!flag)
            return;
        M.toast({html: 'Mensagem enviada'})
    };
};
teste();