/*
 * Copyright (c) 2012, ScalaFX Project
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the ScalaFX Project nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE SCALAFX PROJECT OR ITS CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED
 * AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package scalafx.stage

import java.io.File

import scala.collection.JavaConversions._
import javafx.{stage => jfxs}
import scalafx.Includes._
import scalafx.util.SFXDelegate

object FileChooser {
  implicit def sfxFileChooser2jfx(fc: FileChooser) = fc.delegate

  object ExtensionFilter {
    implicit def sfxExtensionFilter2jfx(ef: ExtensionFilter) = ef.delegate
  }

  class ExtensionFilter(override val delegate: jfxs.FileChooser.ExtensionFilter) extends SFXDelegate[jfxs.FileChooser.ExtensionFilter] {

    /**
     * Creates an ExtensionFilter with the specified description and the file name extensions.
     */
    def this(description: String, extensions: Seq[String]) = this(new jfxs.FileChooser.ExtensionFilter(description, extensions))

    /*
     * Creates an ExtensionFilter with the specified description and the file name extensions.
     * NOTE IMPLEMENTATION: for constructor with extensions varargs compile complaints with message: "double definition: constructor 
     * ExtensionFilter:(description: String, extensions: String*)scalafx.stage.FileChooser.ExtensionFilter and constructor 
     * ExtensionFilter:(description: String, extensions: Seq[String])scalafx.stage.FileChooser.ExtensionFilter 
     * at line XX have same type after erasure: (description: java.lang.String, extensions: Seq)scalafx.stage.FileChooser#ExtensionFilter".
     * So I decided mantain just Seq constructor. 
     */
//        def this(description: String, extensions: String*) = this(new jfxs.FileChooser.ExtensionFilter(description, extensions: _*))

    def description = delegate.getDescription

    def extensions: Seq[String] = delegate.getExtensions

  }
}

class FileChooser(override val delegate: jfxs.FileChooser = new jfxs.FileChooser) extends SFXDelegate[jfxs.FileChooser] {

  /**
   * The initial directory for the displayed dialog.
   */
  def initialDirectory = delegate.initialDirectoryProperty
  def initialDirectory_=(v: File) {
    initialDirectory() = v
  }

  /**
   * The title of the displayed dialog.
   */
  def title = delegate.titleProperty
  def title_=(v: String) {
    title() = v
  }

  /**
   * Gets the extension filters used in the displayed file dialog.
   */
  def extensionFilters = delegate.getExtensionFilters

  /**
   * Shows a new file open dialog.
   */
  def showOpenDialog(ownerWindow: Window) = delegate.showOpenDialog(ownerWindow)

  /**
   * Shows a new file open dialog in which multiple files can be selected.
   */
  def showOpenMultipleDialog(ownerWindow: Window) = delegate.showOpenMultipleDialog(ownerWindow)

  /**
   * Shows a new file save dialog.
   */
  def showSaveDialog(ownerWindow: Window) = delegate.showSaveDialog(ownerWindow)
}