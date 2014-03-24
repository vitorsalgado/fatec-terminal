/*
 * Copyright (C) 2014 Vitor Hugo Salgado <vsalgadopb@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package br.com.fatecpg.sharepoint;

import br.com.fatecpg.repositories.sharepoint.SharepointConnector;
import com.microsoft.schemas.sharepoint.soap.ListsSoap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Vitor Hugo Salgado <vsalgadopb@gmail.com>
 */
public class SharepointConnectorTest {
    
    public SharepointConnectorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * 
     */
    @Test(expected = IllegalArgumentException.class)
    public void null_credentials_should_return_exception() {
        System.out.println("getListsClient -> null_credentials_should_return_exception");
        
        String username = null;
        String password = null;
        String endPointAddress = null;
        
        ListsSoap result = SharepointConnector.getListsClient(username, password, endPointAddress);
    }
    
    @Test(expected = Exception.class)
    public void listSoap_with_invalid_credentials_should_throw_exception(){
        System.out.println("getListsClient -> listSoap_with_invalid_credentials_should_throw_exception");
        
        String username = "error@error";
        String password = "error";
        String endPointAddress = "error";
        
        ListsSoap result = SharepointConnector.getListsClient(username, password, endPointAddress);
        result.getList("Matriculas");
    }
    
}
