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

var numpad0Key = 96;
var numpad1Key = 97;
var numpad2Key = 0;
var numpad3Key = 0;
var numpadAddKey = 107;
var numpadSubtractKey = 109;

var zeroKey = 48;
var oneKey = 49;
var twoKey = 0;
var threeKey = 0;
var subtractKey = 189;
var equalKey = 187;

var currentSelectedOption = 0;
var enroll = '';

$(document).ready(function() {

    $('#txtEnrollment').val('');
    setTimeout($('#txtEnrollment').focus(), 100);

    $(this).ajaxError(function(event, jqxhr, settings, exception) {
        alert('fail');
    });

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
                },
                success: function(response) {
                    if (response.success) {
                        enroll = response.student.enroll;

                        $('#login-errors').hide();
                        $('#login-overlay').fadeOut();
                        $('#student-info').html('Bem-vindo(a), ' + response.student.name);
                    } else {
                        $('#login-errors').html(response.message).fadeIn();
                        setTimeout($('#txtEnrollment').focus(), 100);
                    }
                },
                complete: function() {
                    $('#txtEnrollment').prop('disabled', false);
                    $('#loading-container').hide();
                }
            });
        }
    });

    $(window).keydown(function(e) {
        var key = parseInt(e.which);

        switch (key) {
            case escapeKey:
                if (isTxtEnrollmentFocused() || (enroll == '' && currentSelectedOption == 0))
                    return;

                $.post('./account/logout', null, function() {
                    $('#content').hide();
                    $('#index-content').fadeIn();
                    $('#txtEnrollment').val('');
                    $('#login-overlay').fadeIn();
                    
                    setTimeout($('#txtEnrollment').focus(), 100);

                    currentSelectedOption = 0;
                });

                break;

            case numpad1Key:
            case oneKey:
                if (isTxtEnrollmentFocused() || currentSelectedOption == 1)
                    return;

                currentSelectedOption = 1;

                $.ajax({
                    type: 'GET',
                    data: 'json',
                    url: './student/enrollments',
                    beforeSend: function() {
                        $('#index-content').hide();
                        $('#loading-container').show();
                    },
                    success: function(response) {
                        var table = createMatriculasTable(response);
                        $('#page-content').html('').append(table);
                        $('#page-title').html('Matrículas no Semestre');
                    },
                    complete: function() {
                        $('#content').fadeIn();
                        $('#loading-container').hide();
                    }
                });

                break;

            case numpadAddKey:
            case equalKey:

                if (isTxtEnrollmentFocused() || currentSelectedOption == 0)
                    return;

                document.getElementById('page-content').scrollTop =
                        document.getElementById('page-content').scrollTop + 25;

                break;

            case numpadSubtractKey:
            case subtractKey:

                if (isTxtEnrollmentFocused() || currentSelectedOption == 0)
                    return;

                document.getElementById('page-content').scrollTop =
                        document.getElementById('page-content').scrollTop - 25;
                break;
        }
    });
});


function createMatriculasTable(responseObj) {
    var table = document.createElement('table');
    var tbody = document.createElement('tbody');
    var thead = document.createElement('thead');
    
    var matriculas = responseObj.enrolledDisciplines;

    for (var i = 0; i < matriculas.length; i++) {
        var matricula = matriculas[i];
        var tr = document.createElement('tr');

        createAndAppendTd(tr, matricula.discipline);
        if(matricula.grade1 < 6)
            createAndAppendTd(tr, matricula.grade1, 'red-text');
        else
            createAndAppendTd(tr, matricula.grade1);
        createAndAppendTd(tr, matricula.abscenses1);
        if(matricula.grade2 < 6)
            createAndAppendTd(tr, matricula.grade2, 'red-text');
        else
            createAndAppendTd(tr, matricula.grade2);
        createAndAppendTd(tr, matricula.abscenses2);
        createAndAppendTd(tr, matricula.workGrade);
        createAndAppendTd(tr, matricula.concept);
        if(matricula.grade < 6)
            createAndAppendTd(tr, matricula.grade, 'red-text');
        else
            createAndAppendTd(tr, matricula.grade);

        tbody.appendChild(tr);
    }

    var trHeader = document.createElement('tr');

    createAndAppendTh(trHeader, 'Disciplina');
    createAndAppendTh(trHeader, 'Nota P1');
    createAndAppendTh(trHeader, 'Faltas 1° B.');
    createAndAppendTh(trHeader, 'Nota P2');
    createAndAppendTh(trHeader, 'Faltas 2° B.');
    createAndAppendTh(trHeader, 'NP');
    createAndAppendTh(trHeader, 'Conceito');
    createAndAppendTh(trHeader, 'Média');

    thead.appendChild(trHeader);

    table.className = 'grid';
    table.appendChild(thead);
    table.appendChild(tbody);

    return table;
}

function isTxtEnrollmentFocused() {
    return document.activeElement.id == 'txtEnrollment';
}

function createAndAppendTd(row, value, className) {
    var td = document.createElement('td');
    td.innerHTML = value;
    if (className != null && typeof className != 'undefined' && className != '')
        td.className = className;
    row.appendChild(td);
}

function createAndAppendTh(row, value, className) {
    var th = document.createElement('th');
    th.innerHTML = value;
    if (className != null && typeof className != 'undefined' && className != '')
        th.className = className;
    row.appendChild(th);
}