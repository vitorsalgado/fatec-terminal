/*
 Copyright (C) 2014 Vitor Hugo Salgado <vsalgadopb@gmail.com>
 
 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.
 
 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.
 
 You should have received a copy of the GNU General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
/* 
 Created on : Mar 21, 2014, 11:06:06 PM
 Author     : Vitor Hugo Salgado <vsalgadopb@gmail.com>
 */

var enterKey = 13;
var escapeKey = 27;
var backspaceKey = 8;
var pageUpKey = 33;
var pageDownKey = 34;
var upKey = 38;
var downKey = 40;

var numpad0Key = 96;
var numpad1Key = 97;
var numpad2Key = 98;
var numpadAddKey = 107;
var numpadSubtractKey = 109;

var zeroKey = 48;
var oneKey = 49;
var twoKey = 50;
var subtractKey = 189;
var equalKey = 187;

var currentSelectedOption = 0;
var enrollment = '';

var sessionTimeOut = 480000;
var blockAction = false;

var defaultWelcomeText = 'Bem vindo(a) :-)';

$(document).ready(function() {

    $('#student-info').html(defaultWelcomeText);
    $('#esc-description-text').html('SAIR');
    $('#txtEnrollment').val('');
    setTimeout($('#txtEnrollment').focus(), 100);

    $(this).ajaxError(function(event, jqxhr) {
        $('#login-overlay').hide();
        $('#index-content').hide();
        $('#error-container').html(jqxhr.responseText);
        $('#loading-container').hide();
        $('#error-container').fadeIn();
    });

    //LOGIN
    $('#txtEnrollment').keypress(function(e) {
        if (e.which == enterKey) {
            if ($(this).val() == null || $(this).val() == '') {
                $('#login-errors').html('É necessário informar o número de matrícula para continuar!').fadeIn();
                return;
            }

            if ($(this).val().length < 6) {
                $('#login-errors').html('Número de matrícula inválido!').fadeIn();
                return;
            }

            var enrollment = $(this).val();

            $.ajax({
                type: 'POST',
                url: './account/login',
                dataType: 'json',
                cache: false,
                data: {enrollment: enrollment},
                beforeSend: function() {
                    $('#txtEnrollment').prop('disabled', true);
                    $('#loading-container').show();

                    blockAction = true;
                },
                success: function(response) {
                    if (response.success) {
                        enrollment = response.student.enroll;

                        $('#login-errors').hide();
                        $('#login-overlay').fadeOut();
                        $('#student-info').html('Bem-vindo(a), ' + response.student.name);

                        setTimeout(function() {
                            if (enrollment != null && enrollment != '' && !blockAction && !isTxtEnrollmentFocused()) {
                                logout();
                            }
                        }, sessionTimeOut);

                    } else {
                        $('#login-errors').html(response.message).fadeIn();
                        setTimeout($('#txtEnrollment').focus(), 100);
                    }
                },
                complete: function() {
                    $('#txtEnrollment').prop('disabled', false);
                    $('#loading-container').hide();

                    blockAction = false;
                }
            });
        }
    });

    $(window).keydown(function(e) {
        var key = parseInt(e.which);

        switch (key) {
            
            //VOLTAR / SAIR
            case escapeKey:
            case zeroKey:
            case numpad0Key:

                if (isTxtEnrollmentFocused() || blockAction)
                    return;

                blockAction = true;

                if (currentSelectedOption == 0)
                    logout();
                else
                    showMenu();

                blockAction = false;

                break;

            //MATRÍCULAS NO SEMESTRE
            case numpad1Key:
            case oneKey:

                if (isTxtEnrollmentFocused() || currentSelectedOption != 0 || blockAction)
                    return;

                currentSelectedOption = 1;

                $.ajax({
                    type: 'POST',
                    dataType: 'html',
                    url: './student/enrollments',
                    beforeSend: function() {
                        $('#index-content').hide();
                        $('#loading-container').show();
                        blockAction = true;
                    },
                    success: function(response) {
                        $('#page-content').html(response);
                        $('#esc-description-text').html('VOLTAR');
                    },
                    complete: function() {
                        $('#content').fadeIn();
                        $('#loading-container').hide();
                        blockAction = false;
                    }
                });

                break;

            //HISTÓRICO COMPLETO
            case numpad2Key:
            case twoKey:

                if (isTxtEnrollmentFocused() || currentSelectedOption != 0 || blockAction)
                    return;

                currentSelectedOption = 2;

                $.ajax({
                    type: 'POST',
                    dataType: 'html',
                    url: './student/history',
                    beforeSend: function() {
                        $('#index-content').hide();
                        $('#loading-container').show();
                        blockAction = true;
                    },
                    success: function(response) {
                        $('#page-content').html(response);
                        $('#esc-description-text').html('VOLTAR');
                    },
                    complete: function() {
                        $('#content').fadeIn();
                        $('#loading-container').hide();
                        blockAction = false;
                    }
                });

                break;

            //SCROLL UP
            case numpadAddKey:
            case equalKey:
            case enterKey:
            case downKey:
            case pageDownKey:

                if (isTxtEnrollmentFocused() || currentSelectedOption == 0)
                    return;

                document.getElementById('body').scrollTop =
                        document.getElementById('body').scrollTop + 25;

                break;

            //SCROLL DOWN
            case numpadSubtractKey:
            case subtractKey:
            case backspaceKey:
            case upKey:
            case pageUpKey:

                if (isTxtEnrollmentFocused() || currentSelectedOption == 0)
                    return;

                document.getElementById('body').scrollTop =
                        document.getElementById('body').scrollTop - 25;
                break;
        }
    });
});

function logout() {
    $.post('./account/logout', null, function() {
        enrollment = '';
        currentSelectedOption = 0;

        $('#content').hide();
        $('#error-container').hide();
        
        $('#index-content').fadeIn();
        $('#txtEnrollment').val('');
        $('#login-overlay').fadeIn();
        
        $('#esc-description-text').html('SAIR');
        $('#student-info').html(defaultWelcomeText);

        setTimeout($('#txtEnrollment').focus(), 100);
    });
}

function showMenu() {
    currentSelectedOption = 0;
    $('#content').hide();
    $('#esc-description-text').html('SAIR');
    $('#index-content').fadeIn();
}

function isTxtEnrollmentFocused() {
    return document.activeElement.id == 'txtEnrollment';
}
