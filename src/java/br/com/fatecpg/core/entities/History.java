/*
 * Copyright (C) 2014 vitor.salgado
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
package br.com.fatecpg.core.entities;

import java.util.List;

/**
 *
 * @author vitor.salgado
 */
public class History {

    private String enroll;
    List<HistoryEntry> entries;
    private double efficiencyPercent;

    public String getEnroll() {
        return enroll;
    }

    public void setEnroll(String enroll) {
        this.enroll = enroll;
    }

    public List<HistoryEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<HistoryEntry> entries) {
        this.entries = entries;
    }

    public double getEfficiencyPercent() {
        return efficiencyPercent;
    }

    public void setEfficiencyPercent(double efficiencyPercent) {
        this.efficiencyPercent = efficiencyPercent;
    }

    public int getTotalCredits() {
        int total = 0;

        for (int i = 0; i < entries.size(); i++) {
            i += entries.get(i).getDiscipline().getCredits();
        }

        return total;
    }

    public int getTotalWorkload() {
        int total = 0;

        for (int i = 0; i < entries.size(); i++) {
            i += entries.get(i).getDiscipline().getTotalWorkload();
        }

        return total;
    }
}
