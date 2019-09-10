/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.falconit.joyform.shared;

/**
 *
 * @author User
 */
public class TestCod {
    public static void main(String args[]){
        try{
            String img = " <img src=\"data:image/png;base64,iVBORw0KGgoAAg7AKzjO3j/kuhBBCCPFewACAFEQyFlff+s9/ldj3X/9r8ntJCJMIC0konGTSICgAOgUZQcweP/EIbrX9v7BwyRch+stjYJ+diuPMzaGmnW4tZIwQQgghRI9nj41VFWhvFVIDg6lT7aHU/ztyT+q01Ip/3bfStxLXVSCBdJllpsmQekLN/IgAegR8OqSOIqn+eU////S/zgn3lW23rDJGrwshhBBCzCm6D0xBuy8Vx6cfHUZq8q+xYeHPIbSrwFQKiGt75uSTGhcuABAE0JoOiXx/6HNISI+i555/DsFXnmpjshxtaK/LxfFh23CzNXP76HUhhBBCSHUEjMbVy2ewMPC3uD52Ane3/hodXdNATE0LmRTFDKlB0SJkhEuLgJmbEkZiPbg69RsQw/8Mvcs/V7bdl6MxZSHBUMyybrbpBibqE0IIIaQmBEzW6PngHzA/+B5i06ewqOVddLVPAKoAJFUgiduhOhQ0pNKCBUhng/kB+AVAUDEx3Y4ReR1aWtdgXLkfC5d+qaxDYcgYQ8YIIYQQUmMCRuPqyBB8E4cQSpxCW+gDdARuoiMwi6w7JmUQMyzJTDy94nX/C9CVsfBhOh7CpNKJaXkFEoH7MCU9gsW9a8o6nEYPGSsEQ8YIIYQQUvMCRiOVSuHSh8fRnvwnzBE/RKtwAsFQDAGfABEqBBXwUbyQMqAKQEpIFxVLpoB4QsJsMoIpdSnG8Rvo6duEUEtr2cdRjpCxeoIhY4QQQgipKwFjZP+//TJ62j7CmqUhdId9mNMOtIYEBP3pB+XUMqSkCz1zDSWSwKysYnIGGIumcG4ohivTy/E//bufVXQ81Q4ZK1cei5PtM2SMEEIIIQ0hYAhpBqqdqF9tGDJGCCGEkFLxcQoIKT+yHC1byFi5PSlewcaUhBBCCPECP6eAkPJTr4n6XpRVZsgYIYQQQihgCKkTmqHKmJ14YaI+IYQQQryGIWSElImzU3HPxEsxoVxehn8VA8ULIYQQQsoBk/gJKQPN3JiSifqEEEIIKScMISPEQ+q1t4tXlczodSGEEEIIBQwhdUI9e12cihcrocNEfUIIIYRUCubAEFIishz1RLxUKmellP2YiZc1c/uwPDDFC4EQQgghFYE5MISUQK2EjFWrmSXzXQghhBBSaRhCRkiR1FLIWKXFC0PGCCGEEEIBQ0id0Ay9XexIe10oXgghhBBSHf7/AwAgHYId1h/ZPAAAAABJRU5ErkJggg==\" data-filename=\"Loyalty-management.png\" style=\"width: 816px;\">";
        if( img.contains("<img src=\"data:image")){
            String data = img.substring( img.indexOf("<img src=\"") + "<img src=\"".length(), img.indexOf("\"", img.indexOf("<img src=\"") + 100));
            System.out.println(data);
        }
        }catch(Exception ex){ex.printStackTrace();}
    }
}
